package com.example.analyticsdashboard.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.analyticsdashboard.dto.DataLeftDTO;
import com.example.analyticsdashboard.entity.Plan;
import com.example.analyticsdashboard.entity.Subscriber;
import com.example.analyticsdashboard.entity.SubscriberUsage;
import com.example.analyticsdashboard.repository.PlanRepository;
import com.example.analyticsdashboard.repository.SubscriberRepository;
import com.example.analyticsdashboard.repository.UsageRepository;

public class UsageServiceTest {

    @InjectMocks
    private UsageService usageService;

    @Mock
    private PlanRepository planRepository;

    @Mock
    private SubscriberRepository subscriberRepository;

    @Mock
    private UsageRepository usageRepository;
    
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAssignResources() {
        String subscriberId = "123456789123456789123456";
        String subscriberId2 = "123456789123456789123458";
        String planId = "123456789123456789123457";
        String planId2 = "123456789123456789123458";
        

        Plan samplePlan = new Plan();
        samplePlan.setPlanId(planId);
        samplePlan.setPlanType("Prepaid");
        samplePlan.setDataUnit("GB");
        samplePlan.setPlanName("A");
        samplePlan.setDataPerDay(1);
        samplePlan.setDataPerPack(1);
        samplePlan.setTotalSMS(100);
        samplePlan.setTalkTime(100);
        samplePlan.setValidity(100);
        Plan samplePlan2 = new Plan();
        samplePlan2.setPlanId(planId2);
        samplePlan2.setPlanType("Postpaid");
        samplePlan2.setDataUnit("GB");
        samplePlan2.setPlanName("A");
        samplePlan2.setDataPerDay(1);
        samplePlan2.setDataPerPack(2);
        samplePlan2.setTotalSMS(100);
        Mockito.when(planRepository.findById(planId)).thenReturn(java.util.Optional.of(samplePlan));
        Mockito.when(planRepository.findById(planId2)).thenReturn(java.util.Optional.of(samplePlan2));
        Subscriber sampleSubscriber = new Subscriber();
        sampleSubscriber.setSubscriberID(subscriberId);
        Subscriber sampleSubscriber2 = new Subscriber();
        sampleSubscriber.setSubscriberID(subscriberId2);
        sampleSubscriber.setPlanName("B");
        sampleSubscriber2.setPlanName("C");
        Mockito.when(subscriberRepository.findBySubscriberID(subscriberId)).thenReturn(sampleSubscriber);
        Mockito.when(subscriberRepository.findBySubscriberID(subscriberId2)).thenReturn(sampleSubscriber2);
   
        SubscriberUsage sampleUsage = new SubscriberUsage();
        
        Mockito.when(planRepository.findByPlanName(sampleSubscriber.getPlanName())).thenReturn(java.util.Optional.of(samplePlan));
        
        Mockito.when(usageRepository.save(Mockito.any(SubscriberUsage.class))).thenReturn(sampleUsage);

        

        SubscriberUsage usage2 =  usageService.assignResources(subscriberId, planId);
        SubscriberUsage usage3 =  usageService.assignResources(subscriberId2, planId2);
        

        assertEquals(subscriberId, usage2.getSubscriberID());
        assertEquals("123456789123456789123457", samplePlan.getPlanId().toHexString());
        assertEquals("Prepaid", samplePlan.getPlanType());
        assertEquals("A", sampleSubscriber.getPlanName());
        assertEquals("Daily", usage2.getRenewalType());
        assertEquals(100, usage2.getTalkTimeLeft());
        assertEquals(100, usage2.getValidity());
        assertEquals("Pack", usage3.getRenewalType());

        assertEquals(1024,(int) usage2.getDataLeft());
        assertEquals(1024*2,(int) usage3.getDataLeft());
        assertEquals(100, usage2.getSmsLeft());


        
    } 

    @Test
    public void testReduceValidityForAllSubscribers() {
        SubscriberUsage s1 = new SubscriberUsage();
        s1.setValidity(20);
        SubscriberUsage s2 = new SubscriberUsage();
        s2.setValidity(20);
        List<SubscriberUsage> subsList = new ArrayList<>();
        subsList.add(s1);
        subsList.add(s2);
        Mockito.when(usageRepository.findAll()).thenReturn(subsList);

        // Call the method to be tested
        usageService.reduceValidityForAllSubscribers();

        assertEquals(19, s1.getValidity());
        assertEquals(19, s2.getValidity());
        
    }

    
    @Test
    public void testGetAllUsages() {
    	SubscriberUsage s1 = new SubscriberUsage();
        s1.setValidity(20);
        SubscriberUsage s2 = new SubscriberUsage();
        s2.setValidity(20);
        List<SubscriberUsage> sampleUsages = new ArrayList<>();
        
    	Mockito.when(usageRepository.findAll()).thenReturn(sampleUsages);

    	List<SubscriberUsage> usages = usageService.getAllUsages();
    	assertEquals(sampleUsages, usages);
    }

    @Test
    public void testSmsSent() {


    	 String subscriberId = "user1";
         SubscriberUsage sampleUsage = new SubscriberUsage();
         sampleUsage.setSubscriberID(subscriberId);
         sampleUsage.setSmsLeft(10);

         Mockito.when(usageRepository.findBySubscriberID(subscriberId)).thenReturn(sampleUsage);

         usageService.smsSent(subscriberId);

         assertEquals(9, sampleUsage.getSmsLeft());
        
         Mockito.verify(usageRepository).save(sampleUsage);
    }

    @Test
    public void testGetSubscriberUsage() {
    	String subscriberId = "user1";
        SubscriberUsage sampleUsage = new SubscriberUsage();
        sampleUsage.setSubscriberID(subscriberId);
        
        Mockito.when(usageRepository.findBySubscriberID(subscriberId)).thenReturn(sampleUsage);

        SubscriberUsage result = usageService.getSubscriberUsage(subscriberId);
        assertEquals(sampleUsage, result);
    }

    @Test
    public void testCallMade() {
    	String subscriberId = "user1";
        SubscriberUsage sampleUsage = new SubscriberUsage();
        sampleUsage.setSubscriberID(subscriberId);
        sampleUsage.setTalkTimeLeft(60); // 60 minutes

        // Mock the behavior of usageRepository.findBySubscriberID()
        Mockito.when(usageRepository.findBySubscriberID(subscriberId)).thenReturn(sampleUsage);

        int seconds = 30; // 30 seconds
        usageService.callMade(subscriberId, seconds);

        // Verify that the talk time has been reduced by 0.5 (30 seconds = 0.5 minutes)
        assertEquals(59.5, sampleUsage.getTalkTimeLeft());
        // Verify that usageRepository.save was called
        Mockito.verify(usageRepository).save(sampleUsage);
    }

    @Test
    public void testRenewData() {
    	Subscriber sub1 = new Subscriber();
    	Subscriber sub2 = new Subscriber();
    	String subscriberId = "123456789123456789123456";
    	String subscriberId2 = "123456789123456789123457";
    	sub1.setSubscriberID(subscriberId);
    	sub2.setSubscriberID(subscriberId2);
    	sub1.setPlanName("A");
    	sub2.setPlanName("A");
    	
    	Mockito.when(subscriberRepository.findBySubscriberID(subscriberId)).thenReturn(sub1);
        Mockito.when(subscriberRepository.findBySubscriberID(subscriberId2)).thenReturn(sub2);
   
        Plan plan = new Plan();
        plan.setDataPerDay(200);
        plan.setPlanType("Prepaid");
        plan.setPlanName("A");
        plan.setPlanId("123456789123456789123456");
        plan.setDataUnit("GB");
        
        Mockito.when(planRepository.findById("123456789123456789123456")).thenReturn(java.util.Optional.of(plan));
        Mockito.when(planRepository.findByPlanName("A")).thenReturn(java.util.Optional.of(plan));

    
        List<SubscriberUsage> subsList = new ArrayList<>();
        SubscriberUsage usage1 = new SubscriberUsage();
        
        usage1.setDataLeft(20);
        usage1.setSubscriberID(subscriberId);
        usage1.setRenewalType("Daily");
        SubscriberUsage usage2 = new SubscriberUsage();
        usage2.setDataLeft(20);
        usage2.setSubscriberID(subscriberId2);
        usage2.setRenewalType("Daily");
        subsList.add(usage1);
        subsList.add(usage2);
        Mockito.when(usageRepository.findAll()).thenReturn(subsList);
        
        usageService.assignResources(subscriberId, "123456789123456789123456" );
        usageService.assignResources(subscriberId2, "123456789123456789123456" );
        
        
        usageService.renewData();
        
        assertEquals(200, usage1.getDataLeft());
        assertEquals(200, usage2.getDataLeft());
       
        
    }

    
    @Test
    public void testDataUsedWithDataRemaining() {
        
        String subscriberId = "123456789123456789123456";
        SubscriberUsage sampleUsage = new SubscriberUsage();
        sampleUsage.setSubscriberID(subscriberId);
        sampleUsage.setDataLeft(100); 

        Mockito.when(usageRepository.findBySubscriberID(subscriberId)).thenReturn(sampleUsage);
        String result = usageService.dataUsed(50.0f, subscriberId);

        assertEquals("50.0 MB data used", result);
        assertEquals(50.0f, sampleUsage.getDataLeft(), 0.01); 
    }

    @Test
    public void testDataUsedWithDataQuotaCompleted() {
        
        String subscriberId = "123456789123456789123456";
        SubscriberUsage sampleUsage = new SubscriberUsage();
        sampleUsage.setSubscriberID(subscriberId);
        sampleUsage.setDataLeft(0); 
     
        Mockito.when(usageRepository.findBySubscriberID(subscriberId)).thenReturn(sampleUsage);

        String result = usageService.dataUsed(50.0f, subscriberId);
       
        assertEquals("Data quota completed", result);
        assertEquals(0.0f, sampleUsage.getDataLeft(), 0.01); 
    }

    
    @Test
    public void testDataLeftChart() {
    	
    	String usageId = "123456789123456789123456";
        String subscriberId = "987654321987654321987654";

        // Create sample objects for testing
        SubscriberUsage sampleUsage = new SubscriberUsage();
        sampleUsage.setDataLeft(10.0f); // Data left in GB
        sampleUsage.setRenewalType("Daily");
        sampleUsage.setSubscriberID(subscriberId);
        sampleUsage.setId(usageId);

        Subscriber subscriber = new Subscriber();
        subscriber.setPlanName("PlanB");
        subscriber.setSubscriberID(subscriberId);

        Plan plan = new Plan();
        plan.setPlanName("PlanB");
        plan.setDataUnit("GB");
        plan.setDataPerDay(5.0f);

        // Mock the behavior of repositories
        Mockito.when(usageRepository.findById(usageId)).thenReturn(Optional.of(sampleUsage));
        Mockito.when(subscriberRepository.findBySubscriberID(subscriberId)).thenReturn(subscriber);
        Mockito.when(planRepository.findByPlanName("PlanB")).thenReturn(Optional.of(plan));

        // Call the method to be tested
        DataLeftDTO result = usageService.dataLeftChart(usageId);

        // Verify the expected values
        assertEquals(10.0f, result.getDataLeft(), 0.01); // Using delta for floating-point comparison
        assertEquals(5120.0f, result.getTotalData(), 0.01); // Usin
        
    }

    

    

    
}

