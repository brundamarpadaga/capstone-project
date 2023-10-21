package com.example.analyticsdashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.analyticsdashboard.dto.CallRecordDTO;
import com.example.analyticsdashboard.entity.CallRecord;
import com.example.analyticsdashboard.repository.CallRecordRepository;

@Service
public class CallRecordsService {
	
	@Autowired
    private CallRecordRepository callRecordRepository;
	
	 public CallRecordDTO createCallRecordDTO(CallRecord callRecord) {
	        CallRecordDTO dto = new CallRecordDTO();
	        dto.setId(String.valueOf(callRecord.getId()));
	        dto.setCallStartTime(callRecord.getCallStartTime().toString());
	        dto.setCallEndTime(callRecord.getCallEndTime().toString());
	        dto.setCallDuration(callRecord.getCallDuration());
	        dto.setPhoneNumber(callRecord.getPhoneNumber());
	        dto.setSubscriberID(callRecord.getSubscriberID());
	        return dto;
	    }
	 

	    public CallRecord addCallRecord(CallRecord callRecord) {
	        return callRecordRepository.save(callRecord);
	    }

}
