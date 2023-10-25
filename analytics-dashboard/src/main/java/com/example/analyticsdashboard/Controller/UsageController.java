package com.example.analyticsdashboard.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.analyticsdashboard.entity.SubscriberUsage;
import com.example.analyticsdashboard.service.UsageService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UsageController {
	
	@Autowired
	private UsageService usageService;
	
	@PostMapping("/plan-recharge")
	public String assignResourcesOnRecharge(@RequestParam String subscriberId,@RequestParam String planId) {
		usageService.assignResources(subscriberId,planId);	
		return "Recharge Successful";
	}

	@GetMapping("/usage-status")
	public List<SubscriberUsage> getAllUsages() {
		return usageService.getAllUsages();
	}
	
	@GetMapping("/subscriber-usage/{subscriberId}")
	public SubscriberUsage getSubscriberUsage(@PathVariable String subscriberId) {	
		return usageService.getSubscriberUsage(subscriberId);	
	}

}
