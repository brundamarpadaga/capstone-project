package com.example.analyticsdashboard.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.analyticsdashboard.dto.SubscriberAnalyticsDTO;
import com.example.analyticsdashboard.entity.CallRecord;
import com.example.analyticsdashboard.entity.SmsRecord;
import com.example.analyticsdashboard.service.AnalyticsService;
import com.example.analyticsdashboard.service.CallRecordService;
import com.example.analyticsdashboard.service.SmsService;
import com.example.analyticsdashboard.service.SubscriberService;
import com.example.analyticsdashboard.service.UsageService;

@RestController
@CrossOrigin
@RequestMapping("/api/analytics")
public class AnalyticsController {
	
	@Autowired
    private CallRecordService callRecordService;

	@Autowired
    private SmsService smsService;
	
	@Autowired
    private SubscriberService subscriberService;
	
	@Autowired
	private UsageService usageService;
    
    @Autowired
    private AnalyticsService analyticsService;

	 @GetMapping("/call-records-hourly-count")
	    public ResponseEntity<List<Integer>> getCallRecordsHourlyCount() {
	     List<CallRecord> callRecords = callRecordService.getAllCallrecords();
	     List<Integer> hourlyCounts = analyticsService.calculateHourlyCounts(callRecords);
	     return ResponseEntity.ok(hourlyCounts);   
	 }
	 
	 @GetMapping("/average-call-duration")
	 public ResponseEntity<Double> getAverageCallDuration() {
	     List<CallRecord> callRecords = callRecordService.getAllCallrecords();
	     double totalDuration = 0;
	     for (CallRecord callRecord : callRecords) {
	    	 
	         totalDuration += callRecord.getCallDuration();
	     }
	     double averageDuration = totalDuration / callRecords.size();
	     return ResponseEntity.ok(averageDuration);
	 }
	 

	 @GetMapping("/sms-records-hourly-count")
	    public ResponseEntity<List<Integer>> getSmsRecordsHourlyCount() {
	     List<SmsRecord> smsRecords = smsService.getAllSms();
	     List<Integer> hourlyCounts = analyticsService.calculateHourlySmsCounts(smsRecords);
	     return ResponseEntity.ok(hourlyCounts);   
	 }
	 
	 @GetMapping("/subscriber-analytics")
	    public ResponseEntity<SubscriberAnalyticsDTO> getSubscriberAnalytics() {
	        SubscriberAnalyticsDTO analyticsDTO = subscriberService.getSubscriberAnalytics();
	        return ResponseEntity.ok(analyticsDTO);
	    }
	 
	 
	 @GetMapping("/active-call-count")
	 public ResponseEntity<Integer> getActiveCallCount() {
	     int activeCallCount = callRecordService.countActiveCalls();
	     return ResponseEntity.ok(activeCallCount);
	 }

	 
	 @GetMapping("/inactive-plan-count")
	 public ResponseEntity<Integer> getExpiredPlanCount() {
	     int activeCallCount = usageService.getInactiveSubscriberUsages();
	     return ResponseEntity.ok(activeCallCount);
	 }
	 
	 
	
	

}
