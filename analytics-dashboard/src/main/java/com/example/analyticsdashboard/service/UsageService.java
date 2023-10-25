package com.example.analyticsdashboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.analyticsdashboard.entity.Plan;
import com.example.analyticsdashboard.entity.Subscriber;
import com.example.analyticsdashboard.entity.SubscriberUsage;
import com.example.analyticsdashboard.repository.PlanRepository;
import com.example.analyticsdashboard.repository.SubscriberRepository;
import com.example.analyticsdashboard.repository.UsageRepository;

@Service
public class UsageService {
	
	@Autowired
	private PlanRepository planRepository;
	
	@Autowired
	private SubscriberRepository subscriberRepository;
	
	@Autowired
	private UsageRepository usageRepository;

	public void assignResources(String subscriberId,String planId) {
		SubscriberUsage usage = new SubscriberUsage();
		usage.setSubscriberID(subscriberId);
		Plan newPlan = planRepository.findById(planId).orElseGet(null);
		Subscriber subscriber = subscriberRepository.findBySubscriberID(subscriberId);
		subscriber.setPlanName(newPlan.getPlanName());
		subscriberRepository.save(subscriber);
		if(newPlan.getPlanType().equals("Prepaid")) {
			usage.setRenewalType("Daily");
			if( newPlan.getDataUnit().equals("GB")) {
				float dataInMB = newPlan.getDataPerDay() * 1024;
			     usage.setDataLeft(dataInMB);
			}
			else {
				usage.setDataLeft(newPlan.getDataPerDay());
			}	
		}
		else {
			usage.setRenewalType("Pack");
			if(newPlan.getDataUnit().equals("GB")) {
				float dataInMB = newPlan.getDataPerPack() * 1024;
			     usage.setDataLeft(dataInMB);
			}
			else {
				usage.setDataLeft(newPlan.getDataPerPack());
			}
		}
		usage.setUnlimitedCalls(newPlan.isCallsUnlimited());
		if(newPlan.isCallsUnlimited()== false) {
			usage.setTalkTimeLeft(newPlan.getTalkTime());
		}
		
		usage.setSmsLeft(newPlan.getTotalSMS());
		usage.setValidity(newPlan.getValidity());
		usageRepository.save(usage);
		
	}

	public void reduceValidityForAllSubscribers() {
		List<SubscriberUsage> usages = usageRepository.findAll();
		for(SubscriberUsage usage : usages) {
			usage.setValidity(usage.getValidity()-1);
		}
	}

	public List<SubscriberUsage> getAllUsages() {
		return usageRepository.findAll();
	}

	public void smsSent(String subscriberId) {
		SubscriberUsage usage = usageRepository.findBySubscriberID(subscriberId);
		usage.setSmsLeft(usage.getSmsLeft()-1);
		usageRepository.save(usage);
	}

	public SubscriberUsage getSubscriberUsage(String subscriberId) {
		 return usageRepository.findBySubscriberID(subscriberId);
	}
	
	public void callMade(String subscriberId,int seconds) {
		SubscriberUsage usage = usageRepository.findBySubscriberID(subscriberId);
		float durationInMinutes = seconds/60;
		if(usage.isUnlimitedCalls()==false) {
			usage.setTalkTimeLeft(usage.getTalkTimeLeft()- durationInMinutes);	
			usageRepository.save(usage);
		}
	}
}
