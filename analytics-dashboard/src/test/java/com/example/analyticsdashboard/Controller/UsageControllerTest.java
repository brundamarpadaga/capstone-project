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

import com.example.analyticsdashboard.dto.DataLeftDTO;
import com.example.analyticsdashboard.dto.MessageDTO;
import com.example.analyticsdashboard.entity.SubscriberUsage;
import com.example.analyticsdashboard.service.UsageService;

class UsageControllerTest {

	@InjectMocks
	 private UsageController usageController;

	@Mock
	private UsageService usageService;

	@BeforeEach
	public void init() {
	   MockitoAnnotations.openMocks(this);
	 }

    @Test
    public void testAssignResourcesOnRecharge() {
       
        String phone = "555-555-5555";
        String planId = "123"; 
        String response = usageController.assignResourcesOnRecharge(phone, planId);

        assertNotNull(response);
        assertEquals("Recharge Successful", response); 
    }

    @Test
    public void testGetAllUsages() {
        List<SubscriberUsage> expectedUsages = new ArrayList<>(); 

        when(usageService.getAllUsages()).thenReturn(expectedUsages);

        List<SubscriberUsage> actualUsages = usageController.getAllUsages();

        assertNotNull(actualUsages);
        assertEquals(expectedUsages, actualUsages);
    }

    @Test
    public void testGetSubscriberUsage() {
     
        String subscriberId = "123456789123456789123456";
        SubscriberUsage expectedUsage = new SubscriberUsage(); 

        when(usageService.getSubscriberUsage(subscriberId)).thenReturn(expectedUsage);

        SubscriberUsage actualUsage = usageController.getSubscriberUsage(subscriberId);

        assertNotNull(actualUsage);
        assertEquals(expectedUsage, actualUsage); 
    }

    @Test
    public void testDataUsed() {
      
        float dataUsed = 10.5f;
        String subscriberId = "123456789123456789123456";
        String statusReport = "Data used successfully"; 

        when(usageService.dataUsed(dataUsed, subscriberId)).thenReturn(statusReport);

        ResponseEntity<MessageDTO> response = usageController.dataUsed(dataUsed, subscriberId);
        assertNotNull(response);

        MessageDTO messageDTO = response.getBody();
        assertNotNull(messageDTO);
        assertEquals(statusReport, messageDTO.getStatusReport());
    }

    @Test
    public void testDataLeftPieChart() {
       
        String usageId = "123"; 
        DataLeftDTO expectedDataLeft = new DataLeftDTO(); 

        when(usageService.dataLeftChart(usageId)).thenReturn(expectedDataLeft);

        DataLeftDTO actualDataLeft = usageController.dataLeftPieChart(usageId);

        assertNotNull(actualDataLeft);
        assertEquals(expectedDataLeft, actualDataLeft); 
    }

}
