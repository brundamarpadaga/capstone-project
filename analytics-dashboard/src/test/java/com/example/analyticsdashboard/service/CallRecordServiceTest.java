package com.example.analyticsdashboard.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.analyticsdashboard.dto.CallRecordDTO;
import com.example.analyticsdashboard.entity.CallRecord;
import com.example.analyticsdashboard.repository.CallRecordRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CallRecordServiceTest {

    @InjectMocks
    private CallRecordService callRecordService;

    @Mock
    private CallRecordRepository callRecordRepository;

    @Mock
    private UsageService usageService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindCallRecordById() {
        String callRecordId = "123456789123456789123456"; 
        String subsId = "123456789123456789123455";
        CallRecord callRecord = new CallRecord(subsId,null,null,"");
        callRecord.setId(callRecordId);
        when(callRecordRepository.findById(callRecordId)).thenReturn(Optional.of(callRecord));

        CallRecord result = callRecordService.findCallRecordById(callRecordId);

        assertNotNull(result);
        assertEquals(result.getSubscriberID(), subsId);
        assertEquals(result.getId().toHexString(), callRecordId);
    }

    @Test
    public void testGetAllCallrecords() {
        List<CallRecord> callRecords = new ArrayList<>(); 
        
        String callRecordId = "123456789123456789123456"; 
	    String subscriberId = "123456789123456789123456";
	    LocalDateTime callStartTime = LocalDateTime.now(); 
	    CallRecord callRecord = new CallRecord(subscriberId, callStartTime, null, "1233456789");
	    callRecord.setId(callRecordId);
	    String callRecordId2 = "123456789123456789123456"; 
	    String subscriberId2 = "123456789123456789123456"; 
	    LocalDateTime callStartTime2 = LocalDateTime.now();
	    CallRecord callRecord2 = new CallRecord(subscriberId2, callStartTime2, null, "1233456789");
	    callRecord.setId(callRecordId2);
	    
	    callRecords.add(callRecord2);
	    callRecords.add(callRecord);
	    

        when(callRecordRepository.findAll()).thenReturn(callRecords);

        List<CallRecord> result = callRecordService.getAllCallrecords();

        assertNotNull(result);
        assertEquals(result.get(0).getSubscriberID(), subscriberId2);
        assertEquals(result.get(1).getSubscriberID(), subscriberId);
        
    }

    @Test
    public void testGetCallRecord() {
    	 String callRecordId = "123456789123456789123456"; // Provide a valid call record ID
    	    String subscriberId = "123456789123456789123456"; // Provide a valid subscriber ID
    	    LocalDateTime callStartTime = LocalDateTime.now(); // Provide a valid call start time
    	    CallRecord callRecord = new CallRecord(subscriberId, callStartTime, null, "1233456789");
    	    callRecord.setId(callRecordId);
    	    
    	    when(callRecordRepository.findById(callRecordId)).thenReturn(Optional.of(callRecord));

    	    CallRecordDTO result = callRecordService.getCallRecord(callRecordId);

    	    assertNotNull(result);
    	    assertEquals(result.getId(), callRecordId);
    	    assertEquals(result.getSubscriberID(), subscriberId);
       
    }

    @Test
    public void testGetAllCallRecordsBySubscriberId() {
    	String callRecordId = "123456789123456789123456";
        String subscriberId = "123456789123456789123456"; 
        List<CallRecord> callRecords = new ArrayList<>(); 
        CallRecord callRecord = new CallRecord(subscriberId, null, null, "");
        callRecords.add(callRecord);
	    when(callRecordRepository.findById(callRecordId)).thenReturn(Optional.of(callRecord));

        when(callRecordRepository.findAllBySubscriberID(subscriberId)).thenReturn(callRecords);

        List<CallRecord> result = callRecordService.getAllCallRecordsBySubscriberId(subscriberId);

        assertNotNull(result);
        System.out.println(result);
        assertEquals(result.get(0).getSubscriberID(), "123456789123456789123456");
        
    }

    @Test
    public void testGetAllCallRecordsDTOBySubscriberId() {
        String subscriberId = "123456789123456789123456"; 
        List<CallRecord> callRecords = new ArrayList<>(); 
        when(callRecordRepository.findAllBySubscriberID(subscriberId)).thenReturn(callRecords);

        List<CallRecordDTO> result = callRecordService.getAllCallRecordsDTOBySubscriberId(subscriberId);

        assertNotNull(result);
        
    }

    @Test
    public void testCreateCallRecord() {
        String subscriberId = "123456789123456789123456"; 
        String phoneNumber = "phoneNumber"; 

        LocalDateTime currentDateTime = LocalDateTime.now();
        when(callRecordRepository.save(any())).thenReturn(new CallRecord("",currentDateTime,null,""));

        CallRecord result = callRecordService.createCallRecord(subscriberId, phoneNumber);

        assertNotNull(result);
        
    }

    @Test
    public void testEndCall() {
        String callRecordId = "123456789123456789123456"; 
        
        String subscriberId = "123456789123456789123456";
        CallRecord callRecord = new CallRecord(subscriberId,LocalDateTime.now(),null,"123447826"); 
        callRecord.setId(callRecordId);
        callRecord.setCallActive(true);
        when(callRecordRepository.findById(callRecordId)).thenReturn(Optional.of(callRecord));

        String result = callRecordService.endCall(callRecordId);

        assertEquals("Call Ended",result);
    }

    @Test
    public void testEndCallNonExistent() {
        String callRecordId = "nonExistentCallRecordId"; 

        when(callRecordRepository.findById(callRecordId)).thenReturn(Optional.empty());
        
        String result = callRecordService.endCall(callRecordId);

        assertEquals("Call not found or already ended", result);
    }

    @Test
    public void testConvertToDTO() {
    	String callRecordId = "123456789123456789123456";
        CallRecord callRecord = new CallRecord("123456789123456789123456",LocalDateTime.now(),LocalDateTime.now(),"123456789"); // Create a CallRecord instance with sample data
        callRecord.setCallActive(true);
        callRecord.setId(callRecordId);
        when(callRecordRepository.findById(callRecordId)).thenReturn(Optional.of(callRecord));
        CallRecordDTO result = callRecordService.convertToDTO(callRecord);

        assertNotNull(result);
        
    }

    
    @Test
    public void testGetAllCallrecordsDTO() {
        List<CallRecord> callRecords = new ArrayList<>(); 

        
        String callRecordId = "123456789123456789123456";
        CallRecord callRecord = new CallRecord("123456789123456789123456",LocalDateTime.now(),LocalDateTime.now(),"123456789"); // Create a CallRecord instance with sample data
        callRecord.setCallActive(true);
        callRecord.setId(callRecordId);
        when(callRecordRepository.findById(callRecordId)).thenReturn(Optional.of(callRecord));
        
        callRecords.add(callRecord);
        when(callRecordRepository.findAll()).thenReturn(callRecords);

        List<CallRecordDTO> result = callRecordService.getAllCallrecordsDTO();

        assertNotNull(result);
        assertEquals(result.get(0).getId(), callRecordId);
        
    }

    @Test
    public void testGetActiveCalls() {
        List<CallRecord> callRecords = new ArrayList<>(); 
        
        String callRecordId = "123456789123456789123456"; 
        String subsId = "123456789123456789123455";
        CallRecord callRecord = new CallRecord(subsId,null,null,""); //
        callRecord.setId(callRecordId);
        
        callRecord.setCallActive(true);
        callRecords.add(callRecord);

        when(callRecordRepository.findAll()).thenReturn(callRecords);

        List<CallRecordDTO> result = callRecordService.getActiveCalls();

        assertNotNull(result);
        
        
        
    }
    
    @Test
    public void testCountActiveCalls()  {
    	List<CallRecord> callRecords = new ArrayList<>(); 
    	String callRecordId = "123456789123456789123456"; 
        String subsId = "123456789123456789123455";
        CallRecord callRecord = new CallRecord(subsId,null,null,"1234"); //
        callRecord.setId(callRecordId);
        
        callRecord.setCallActive(true);
        callRecords.add(callRecord);
        when(callRecordRepository.findAll()).thenReturn(callRecords);
        int count = callRecordService.countActiveCalls();
        
        assertNotNull(count);;
    
    	
    }
}
