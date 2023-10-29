package com.example.analyticsdashboard.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.analyticsdashboard.dto.PlanDTO;
import com.example.analyticsdashboard.entity.Plan;
import com.example.analyticsdashboard.repository.PlanRepository;

class PlanServiceTest {

	@InjectMocks
    private PlanService planService;

    @Mock
    private PlanRepository planRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddPlan() {
        Plan plan = new Plan(); 

        when(planRepository.save(any())).thenReturn(plan);

        String result = planService.addPlan(plan);

        assertEquals("Plan added successfully", result);
    }

    @Test
    public void testGetAllPlans() {
        List<Plan> plans = new ArrayList<>(); 
        when(planRepository.findAll()).thenReturn(plans);

        List<PlanDTO> result = planService.getAllPlans();

        assertNotNull(result);
       
    }

    @Test
    public void testEditPlan() {
        String id = "planId"; 
        Plan plan = new Plan(); 

        when(planRepository.findById(id)).thenReturn(Optional.of(new Plan())); 

        String result = planService.editPlan(id, plan);

        assertEquals("Plan added successfully", result);
    }

    @Test
    public void testEditPlanNonExistent() {
        String id = "nonExistentPlanId"; 
        Plan plan = new Plan(); 

        when(planRepository.findById(id)).thenReturn(Optional.empty()); 

        String result = planService.editPlan(id, plan);

        assertEquals("Plan does not exist", result);
    }

    @Test
    public void testDeletePlan() {
        String id = "planId"; 

        String result = planService.deletePlan(id);

        assertEquals("Plan deleted successfully", result);
    }

    @Test
    public void testGetPlan() {
        String id = "123456789123456789123456"; 
        Plan plan = new Plan(); 
        plan.setPlanId(id);
        when(planRepository.findByPlanId(id)).thenReturn(Optional.of(plan));

        PlanDTO result = planService.getPlan(id);

        assertNotNull(result);
        assertEquals(result.getPlanId(), id);
       
    }

    @Test
    public void testGetPlanNonExistent() {
        String id = "123456789123456789123456";
        when(planRepository.findByPlanId(id)).thenReturn(Optional.empty());

        PlanDTO result = planService.getPlan(id);

        assertEquals(null, result);
    }
    
    @Test
    public void testConvertToPlanDTO() {

        Plan plan = new Plan();
        plan.setPlanId("123456789123456789123456"); 
        plan.setPlanName("Sample Plan");
        plan.setPlanType("Type A");
        plan.setValidity(30);
        plan.setTotalSMS(1000);
        plan.setCallsUnlimited(true);
        plan.setTalkTime(600);
        plan.setDataPerDay(1);
        plan.setDataPerPack(10);
        plan.setDataUnit("GB");

        PlanService planService = new PlanService();
        PlanDTO planDTO = planService.convertToPlanDTO(plan);

        assertNotNull(planDTO);

        assertEquals("123456789123456789123456", planDTO.getPlanId());
        assertEquals("Sample Plan", planDTO.getPlanName());
        assertEquals("Type A", planDTO.getPlanType());
        assertEquals(30, planDTO.getValidity());
        assertEquals(1000, planDTO.getTotalSMS());
        assertTrue(planDTO.isCallsUnlimited());
        assertEquals(600, planDTO.getTalkTime());
        assertEquals(1, planDTO.getDataPerDay());
        assertEquals(10, planDTO.getDataPerPack());
        assertEquals("GB", planDTO.getDataUnit());
        
    }

}
