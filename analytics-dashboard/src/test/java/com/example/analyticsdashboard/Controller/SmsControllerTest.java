package com.example.analyticsdashboard.Controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.example.analyticsdashboard.dto.MessageDTO;
import com.example.analyticsdashboard.dto.SmsRecordDTO;
import com.example.analyticsdashboard.entity.SmsRecord;
import com.example.analyticsdashboard.service.SmsService;

class SmsControllerTest {
    
    
    @InjectMocks
	 private SmsController smsController;

	@Mock
	private SmsService smsService;

	@BeforeEach
	public void init() {
	   MockitoAnnotations.openMocks(this);
	 }

 

    @Test
    public void testGetAllSmsRecords() {
        
        List<SmsRecord> expectedSmsRecords = new ArrayList<>(); 

        when(smsService.getAllSms()).thenReturn(expectedSmsRecords);

        List<SmsRecord> actualSmsRecords = smsController.getAllSmsRecords();

        assertNotNull(actualSmsRecords);
        assertEquals(expectedSmsRecords, actualSmsRecords); 
    }

    @Test
    public void testGetAllSmsRecordsDTO() {
      
        List<SmsRecordDTO> expectedSmsRecords = new ArrayList<>(); 

        when(smsService.getAllSmsDTO()).thenReturn(expectedSmsRecords);

        List<SmsRecordDTO> actualSmsRecords = smsController.getAllSmsRecordsDTO();

        assertNotNull(actualSmsRecords);
        assertEquals(expectedSmsRecords, actualSmsRecords);
    }

    @Test
    public void testGetAllSmsRecordsBySubscriber() {
       
        String subscriberId = "123456789123456789123456";
        List<SmsRecordDTO> expectedSmsRecords = new ArrayList<>(); 

        when(smsService.getAllSmsRecordsBySubscriber(subscriberId)).thenReturn(expectedSmsRecords);

        List<SmsRecordDTO> actualSmsRecords = smsController.getAllSmsRecordsBySubscriber(subscriberId);

        assertNotNull(actualSmsRecords);
        assertEquals(expectedSmsRecords, actualSmsRecords); 
    }

    @Test
    public void testAddSmsRecord() {
        
        String subscriberId = "123456789123456789123456";
        String phoneNumber = "555-555-5555";
        String statusReport = "Sms added successfully";

        when(smsService.addSmsRecord(subscriberId, phoneNumber)).thenReturn(statusReport);

        ResponseEntity<MessageDTO> response = smsController.addSmsRecord(subscriberId, phoneNumber);
        assertNotNull(response);

        MessageDTO messageDTO = response.getBody();
        assertNotNull(messageDTO);
        assertEquals(statusReport, messageDTO.getStatusReport());
    }
}
