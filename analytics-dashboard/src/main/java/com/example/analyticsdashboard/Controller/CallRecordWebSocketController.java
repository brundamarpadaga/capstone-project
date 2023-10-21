package com.example.analyticsdashboard.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.analyticsdashboard.dto.CallRecordDTO;
import com.example.analyticsdashboard.entity.CallRecord;
import com.example.analyticsdashboard.repository.CallRecordRepository;
import com.example.analyticsdashboard.service.CallRecordsService;

@Controller
@CrossOrigin
public class CallRecordWebSocketController {
	
	 
	 @Autowired
	 private CallRecordsService callRecordsService;

	 @MessageMapping("/call-records")
	 @SendTo("/topic/call-records")
	 public CallRecordDTO addCallRecord(CallRecord callRecord) 
	 {
		 CallRecord savedCallRecord = callRecordsService.addCallRecord(callRecord);
		 CallRecordDTO callRecordDTO = callRecordsService.createCallRecordDTO(savedCallRecord);
	     return callRecordDTO;  
	 }
}
