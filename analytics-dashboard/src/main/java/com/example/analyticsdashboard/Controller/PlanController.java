package com.example.analyticsdashboard.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.analyticsdashboard.dto.PlanDTO;
import com.example.analyticsdashboard.entity.Plan;
import com.example.analyticsdashboard.service.PlanService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class PlanController {
	
	@Autowired
	private PlanService planService;	
	
	@GetMapping("/plans")
	public List<PlanDTO> getAllPlans(){
		return planService.getAllPlans();
	}
	
	
	@PostMapping("/plan/add")
	public ResponseEntity<String> addPlan(@RequestBody Plan plan) {
		return ResponseEntity.ok(planService.addPlan(plan));
	}
	
	 @PutMapping("/plan/edit/{id}")
	 public String editPlan(@PathVariable String id, @RequestBody Plan plan) {
		 return planService.editPlan(id, plan);
	        
	 }

	    @DeleteMapping("/plan/delete/{id}")
	    public String deletePlan(@PathVariable String id) {
	        return planService.deletePlan(id);
	    }
	    
	    @GetMapping("/plan/{id}")
	    public PlanDTO getPlan(@PathVariable String id) {
	    	return planService.getPlan(id);
	    	
	    }

}
