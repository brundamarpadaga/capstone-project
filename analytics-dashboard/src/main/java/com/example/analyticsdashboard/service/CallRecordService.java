package com.example.analyticsdashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.analyticsdashboard.dto.CallRecordDTO;
import com.example.analyticsdashboard.entity.CallRecord;
import com.example.analyticsdashboard.repository.CallRecordRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CallRecordService {
	
	@Autowired
    private CallRecordRepository callRecordRepository;
	
	@Autowired
	private UsageService usageService;


    public CallRecord findCallRecordById(String callRecordId) {
        return callRecordRepository.findById(callRecordId).orElseGet(null);
    }
    public List<CallRecord> getAllCallrecords() {
        List<CallRecord> callRecords = callRecordRepository.findAll();
        return callRecords;
    }
    
    public CallRecordDTO getCallRecord(String callRecordId) {
    	System.out.println(callRecordId);
    	return convertToDTO( callRecordRepository.findById(callRecordId).get());
    }

    public List<CallRecord> getAllCallRecordsBySubscriberId(String subscriberId) {
        return callRecordRepository.findAllBySubscriberID(subscriberId);
    }
    public List<CallRecordDTO> getAllCallRecordsDTOBySubscriberId(String subscriberId) {
        List<CallRecord> callRecords =callRecordRepository.findAllBySubscriberID(subscriberId);
        List <CallRecordDTO> callRecordDTOs = new ArrayList<CallRecordDTO>();
        for( CallRecord callRecord: callRecords) {
			callRecordDTOs.add(convertToDTO(callRecord));
		}
		return callRecordDTOs;
    }

    public CallRecord createCallRecord(String subscriberId, String phoneNumber) {
        LocalDateTime callStartTime = LocalDateTime.now();
        CallRecord callRecord = new CallRecord(subscriberId, callStartTime, null, phoneNumber);
        callRecord.setCallActive(true);
        return callRecordRepository.save(callRecord);
    }

    public String endCall(String callRecordId) {
        CallRecord callRecord = callRecordRepository.findById(callRecordId).orElse(null);
        String subscriberId = callRecord.getSubscriberID();
        if (callRecord.getCallEndTime() == null && callRecord.isCallActive()) {
			LocalDateTime callEndTime = LocalDateTime.now();
			callRecord.setCallEndTime(callEndTime);
			callRecord.setCallActive(false);
			int durationInSeconds = callRecord.calculateCallDuration();
			callRecordRepository.save(callRecord);
			System.out.println(durationInSeconds);
			usageService.callMade(subscriberId, durationInSeconds);
			return "Call Ended";
		} else {
			return "Call not found or already ended";
		}
        
    }
    
    public CallRecordDTO convertToDTO(CallRecord callRecord) {
    	CallRecordDTO dto = new CallRecordDTO();
    	dto.setSubscriberID(callRecord.getSubscriberID());
    	dto.setCallStartTime(callRecord.getCallStartTime().toString());
    	dto.setId(callRecord.getId().toHexString());
    	dto.setCallActive(callRecord.isCallActive());
    	if(callRecord.getCallEndTime() != null) {
    		dto.setCallEndTime(callRecord.getCallEndTime().toString());
        	dto.setCallDuration(callRecord.getCallDuration());
    	}
    	dto.setPhoneNumber(callRecord.getPhoneNumber());
    	return dto;
    }

	public List<CallRecordDTO> getAllCallrecordsDTO() {
		List<CallRecord> callRecords = callRecordRepository.findAll();
		List<CallRecordDTO> callRecordDTOs = new ArrayList<>();
		for( CallRecord callRecord: callRecords) {
			callRecordDTOs.add(convertToDTO(callRecord));
		}
		return callRecordDTOs;
		
	}
	public List<CallRecordDTO> getActiveCalls() {
		List<CallRecord> callRecords = callRecordRepository.findByCallActive(true);
		List<CallRecordDTO> callRecordDTOs = new ArrayList<>();
		for( CallRecord callRecord: callRecords) {
			callRecordDTOs.add(convertToDTO(callRecord));
		}
		return callRecordDTOs;
	}
	
	public List<Integer> calculateHourlyCounts(List<CallRecord> callRecords) {
	   
	    Map<Integer, Integer> hourlyCounts = new HashMap<>();

	    for (CallRecord callRecord : callRecords) {
	        LocalDateTime startDateTime = callRecord.getCallStartTime();
	        LocalDateTime endDateTime = callRecord.getCallEndTime();

	     
	        while (startDateTime.isBefore(endDateTime)) {
	            int hour = startDateTime.getHour();
	            hourlyCounts.put(hour, hourlyCounts.getOrDefault(hour, 0) + 1);
	            startDateTime = startDateTime.plusHours(1);
	        }
	    }

	    // Convert the map to a list
	    List<Integer> hourlyCountList = new ArrayList<>();
	    for (int hour = 0; hour < 24; hour++) {
	        hourlyCountList.add(hourlyCounts.getOrDefault(hour, 0));
	    }

	    return hourlyCountList;
	}
}
