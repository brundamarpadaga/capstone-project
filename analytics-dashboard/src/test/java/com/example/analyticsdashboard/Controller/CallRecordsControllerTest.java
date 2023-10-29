package com.example.analyticsdashboard.Controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.example.analyticsdashboard.dto.CallRecordDTO;
import com.example.analyticsdashboard.dto.MessageDTO;
import com.example.analyticsdashboard.entity.CallRecord;
import com.example.analyticsdashboard.service.CallRecordService;

class CallRecordsControllerTest {

	 @InjectMocks
	 private CallRecordsController callRecordsController;

	    @Mock
	    private CallRecordService callRecordService;

	    @BeforeEach
	    public void init() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    public void testGetCallRecord() {

	        String callRecordId = "123456789";


	        CallRecordDTO callRecordDTO = new CallRecordDTO();
	        callRecordDTO.setId(callRecordId);

	    
	        when(callRecordService.getCallRecord(callRecordId)).thenReturn(callRecordDTO);

	  
	        CallRecordDTO result = callRecordsController.getCallRecord(callRecordId);

	
	        assertNotNull(result);
	        assertEquals(callRecordId, result.getId());
	    }

	    @Test
	    public void testGetAllCallRecordsOfSubscriber() {
	       
	        String subscriberId = "987654321";
	   
	        List<CallRecord> callRecords = List.of(new CallRecord(subscriberId,null,null,""), new CallRecord(subscriberId,null,null,""));
	        when(callRecordService.getAllCallRecordsBySubscriberId(subscriberId)).thenReturn(callRecords);
	        List<CallRecord> result = callRecordsController.getAllCallRecordsOfSubscriber(subscriberId);
	        assertNotNull(result);
	        assertEquals(callRecords.size(), result.size());
	    }

	    @Test
	    public void testStartCall() {
	        String subscriberId = "123456789123456789123456";
	        String phone = "555-555-5555";

	        when(callRecordService.createCallRecord(subscriberId, phone)).thenReturn(new CallRecord(subscriberId,null,null,phone));

	        ResponseEntity<MessageDTO> response = callRecordsController.startCall(subscriberId, phone);
	        assertNotNull(response);
	     

	        MessageDTO messageDTO = response.getBody();
	        assertNotNull(messageDTO);
	        assertEquals("Call Started", messageDTO.getStatusReport());
	    }

	    @Test
	    public void testEndCall() {

	        String callRecordId = "987654";
	        when(callRecordService.endCall(callRecordId)).thenReturn("Call Ended");
	        ResponseEntity<MessageDTO> response = callRecordsController.endCall(callRecordId);
	        assertNotNull(response);
	        

	        MessageDTO messageDTO = response.getBody();
	        assertNotNull(messageDTO);
	        assertEquals("Call Ended", messageDTO.getStatusReport());
	    }

	    @Test
	    public void testGetActiveCallRecords() {

	        List<CallRecordDTO> callRecordDTOs = List.of(new CallRecordDTO(), new CallRecordDTO());
	        when(callRecordService.getActiveCalls()).thenReturn(callRecordDTOs);
	        List<CallRecordDTO> result = callRecordsController.getActiveCallRecords();
	        assertNotNull(result);
	        assertEquals(callRecordDTOs.size(), result.size());
	    }
	    

        @Test
        public void testGetAllCallRecordsDTOsOfSubscriber() {
            // Mock the behavior of callRecordService method
            String subscriberId = "123456789123456789123456";
            List<CallRecordDTO> expectedCallRecords = new ArrayList<>(); // Set your expected data

            when(callRecordService.getAllCallRecordsDTOBySubscriberId(subscriberId)).thenReturn(expectedCallRecords);

            List<CallRecordDTO> actualCallRecords = callRecordsController.getAllCallRecordsDTOsOfSubscriber(subscriberId);

            assertNotNull(actualCallRecords);
            assertEquals(expectedCallRecords, actualCallRecords); // Add appropriate assertions
        }

        @Test
        public void testGetAllCallRecords() {
       
            List<CallRecord> expectedCallRecords = new ArrayList<>(); // Set your expected data

            when(callRecordService.getAllCallrecords()).thenReturn(expectedCallRecords);

            List<CallRecord> actualCallRecords = callRecordsController.getAllCallRecords();

            assertNotNull(actualCallRecords);
            assertEquals(expectedCallRecords, actualCallRecords); // Add appropriate assertions
        }

        @Test
        public void testGetAllCallRecordsDTO() {
            // Mock the behavior of callRecordService method
            List<CallRecordDTO> expectedCallRecords = new ArrayList<>(); // Set your expected data

            when(callRecordService.getAllCallrecordsDTO()).thenReturn(expectedCallRecords);

            List<CallRecordDTO> actualCallRecords = callRecordsController.getAllCallRecordsDTO();

            assertNotNull(actualCallRecords);
            assertEquals(expectedCallRecords, actualCallRecords); // Add appropriate assertions
        }


}
