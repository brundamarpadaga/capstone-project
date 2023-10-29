package com.example.analyticsdashboard.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.analyticsdashboard.dto.SubscriberAnalyticsDTO;
import com.example.analyticsdashboard.dto.SubscriberDTO;
import com.example.analyticsdashboard.entity.Plan;
import com.example.analyticsdashboard.entity.Subscriber;
import com.example.analyticsdashboard.repository.PlanRepository;
import com.example.analyticsdashboard.repository.SubscriberRepository;

public class SubscriberServiceTest {

    @Mock
    private SubscriberRepository subscriberRepository;

    @Mock
    private PlanRepository planRepository;

    private SubscriberService subscriberService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        subscriberService = new SubscriberService(subscriberRepository, planRepository);
    }

    @Test
    public void testGetAllSubscriberDetails() {
        
        Subscriber subscriber = new Subscriber();
        subscriber.setSubscriberID("123456789123456789123456");
        subscriber.setPlanName("TestPlanName");
        subscriber.setLocation("Hyderabad");

        Plan plan = new Plan();
        plan.setPlanName("TestPlanName");
        plan.setLocationBasedPricing(Map.of("Hyderabad", 100));

        when(subscriberRepository.findAll()).thenReturn(List.of(subscriber));
        when(planRepository.findByPlanName("TestPlanName")).thenReturn(Optional.of(plan));

        List<SubscriberDTO> result = subscriberService.getAllSubscriberDetails();

        
        assertEquals(1, result.size());
        assertEquals("123456789123456789123456", result.get(0).getSubscriber().getSubscriberID());
        assertEquals("Hyderabad", result.get(0).getSubscriber().getLocation());
        assertEquals(100, result.get(0).getLocationBasedPricing());
        
    }
  

    @Test
    public void testGetAllSubs() {
        // Mock data
        Subscriber subscriber1 = new Subscriber();
        subscriber1.setName("1");
        Subscriber subscriber2 = new Subscriber();
        subscriber2.setName("2");

        when(subscriberRepository.findAll()).thenReturn(List.of(subscriber1, subscriber2));

        List<Subscriber> result = subscriberService.getAllSubs();

        
        assertEquals(2, result.size());
        assertEquals("1", result.get(0).getName());
        assertEquals("2", result.get(1).getName());
       
    }

    @Test
    public void testAddSubscriber() {
        
        Subscriber subscriber = new Subscriber();
        subscriber.setName("1");
        subscriber.setPlanName("a");
        subscriber.setSubscriberID("123456789123456789123456");

        subscriberService.addSubscriber(subscriber);
        when(subscriberRepository.findBySubscriberID("123456789123456789123456")).thenReturn(subscriber);
        
        assertEquals("123456789123456789123456", subscriber.getSubscriberID());

        
    }
    
    @Test
    public void testGetSubscriberAnalytics() {
        // Arrange
        List<Subscriber> subscribers = new ArrayList<>();
        
        Subscriber subscriber = new Subscriber();
        subscriber.setName("1");
        subscriber.setPlanName("a");
        subscriber.setSubscriberID("123456789123456789123456");
        
        Subscriber subscriber2 = new Subscriber();
        subscriber2.setName("2");
        subscriber2.setPlanName("b");
        subscriber2.setSubscriberID("123456789123456789123457");
        
        subscribers.add(subscriber2);
        subscribers.add(subscriber);
        
        when(subscriberRepository.findAll()).thenReturn(subscribers);
        
        Plan plan1 = new Plan();
        plan1.setPlanName("a");
        plan1.setPlanType("Prepaid");
        plan1.setPlanId("123456789123456789123458");
        Plan plan2 = new Plan();
        plan2.setPlanName("b");
        plan2.setPlanType("Postpaid");
        plan2.setPlanId("123456789123456789123459");
        
        when(planRepository.findByPlanName("a")).thenReturn(Optional.of(plan1));
        when(planRepository.findByPlanName("b")).thenReturn(Optional.of(plan2));
        
        List<Plan> plans = new ArrayList<Plan>();
        
        when(planRepository.findAll()).thenReturn(plans);
       
        SubscriberAnalyticsDTO result = subscriberService.getSubscriberAnalytics();

        assertEquals(2,result.getTotalSubscribers());
        assertEquals(1,result.getPostpaidSubscribers());
        assertEquals(1,result.getPrepaidSubscribers());
    }
    
    @Test
    public void testGetSubscriberDetails() {
        
        
        Subscriber subscriber = new Subscriber();
        subscriber.setName("1");
        subscriber.setPlanName("a");
        subscriber.setSubscriberID("123456789123456789123456");
        
        when(subscriberRepository.findBySubscriberID("123456789123456789123456")).thenReturn(subscriber);
        
        Subscriber testSub = subscriberService.getSubscriberDetails("123456789123456789123456");
        
        assertEquals("1", testSub.getName());
        assertEquals("a", testSub.getPlanName());
        assertEquals("123456789123456789123456", testSub.getSubscriberID());
    }

}