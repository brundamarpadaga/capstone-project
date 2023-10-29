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

import com.example.analyticsdashboard.dto.SubscriberAnalyticsDTO;
import com.example.analyticsdashboard.entity.CallRecord;
import com.example.analyticsdashboard.entity.SmsRecord;
import com.example.analyticsdashboard.service.AnalyticsService;
import com.example.analyticsdashboard.service.CallRecordService;
import com.example.analyticsdashboard.service.SmsService;
import com.example.analyticsdashboard.service.SubscriberService;
import com.example.analyticsdashboard.service.UsageService;

class AnalyticsControllerTest {

	@InjectMocks
	 private AnalyticsController analyticsController;

	@Mock
	private UsageService usageService;
	
	@Mock
	private SubscriberService subscriberService;
	
	@Mock
	private CallRecordService callRecordService;
	
	@Mock
	private SmsService smsService;
	
	@Mock
	private AnalyticsService analyticsService;

	@BeforeEach
	public void init() {
	   MockitoAnnotations.openMocks(this);
	 }

    @Test
    public void testGetCallRecordsHourlyCount() {
        List<CallRecord> callRecords = new ArrayList<>(); 
        List<Integer> expectedHourlyCounts = new ArrayList<>(); 

        when(callRecordService.getAllCallrecords()).thenReturn(callRecords);
        when(analyticsService.calculateHourlyCounts(callRecords)).thenReturn(expectedHourlyCounts);

        ResponseEntity<List<Integer>> response = analyticsController.getCallRecordsHourlyCount();
        assertNotNull(response);

        List<Integer> actualHourlyCounts = response.getBody();
        assertNotNull(actualHourlyCounts);
        assertEquals(expectedHourlyCounts, actualHourlyCounts); 
    }
    
    @Test
    public void testGetAverageCallDuration() {
       
        double expectedAverageDuration = 42.0; 

        when(callRecordService.getAverageCallDuration()).thenReturn(expectedAverageDuration);

        ResponseEntity<Double> response = analyticsController.getAverageCallDuration();
        assertNotNull(response);

        double actualAverageDuration = response.getBody();
        assertEquals(expectedAverageDuration, actualAverageDuration); // Add appropriate assertions
    }

    @Test
    public void testGetSmsRecordsHourlyCount() {
        
        List<SmsRecord> smsRecords = new ArrayList<>(); 
        List<Integer> expectedHourlyCounts = new ArrayList<>();

        when(smsService.getAllSms()).thenReturn(smsRecords);
        when(analyticsService.calculateHourlySmsCounts(smsRecords)).thenReturn(expectedHourlyCounts);

        ResponseEntity<List<Integer>> response = analyticsController.getSmsRecordsHourlyCount();
        assertNotNull(response);

        List<Integer> actualHourlyCounts = response.getBody();
        assertNotNull(actualHourlyCounts);
        assertEquals(expectedHourlyCounts, actualHourlyCounts); 
    }

    @Test
    public void testGetSubscriberAnalytics() {
        SubscriberAnalyticsDTO expectedAnalyticsDTO = new SubscriberAnalyticsDTO(); 

        when(subscriberService.getSubscriberAnalytics()).thenReturn(expectedAnalyticsDTO);

        ResponseEntity<SubscriberAnalyticsDTO> response = analyticsController.getSubscriberAnalytics();
        assertNotNull(response);

        SubscriberAnalyticsDTO actualAnalyticsDTO = response.getBody();
        assertNotNull(actualAnalyticsDTO);
    }

    @Test
    public void testGetActiveCallCount() {
        int expectedActiveCallCount = 10; 

        when(callRecordService.countActiveCalls()).thenReturn(expectedActiveCallCount);

        ResponseEntity<Integer> response = analyticsController.getActiveCallCount();
        assertNotNull(response);

        int actualActiveCallCount = response.getBody();
        assertEquals(expectedActiveCallCount, actualActiveCallCount); 
    }

    @Test
    public void testGetExpiredPlanCount() {
        int expectedExpiredPlanCount = 5; 

        when(usageService.getInactiveSubscriberUsages()).thenReturn(expectedExpiredPlanCount);

        ResponseEntity<Integer> response = analyticsController.getExpiredPlanCount();
        assertNotNull(response);

        int actualExpiredPlanCount = response.getBody();
        assertEquals(expectedExpiredPlanCount, actualExpiredPlanCount); 
    }
    
    

}
