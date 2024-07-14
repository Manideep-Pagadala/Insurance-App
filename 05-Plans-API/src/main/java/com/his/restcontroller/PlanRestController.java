package com.his.restcontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.his.dto.PlanDto;
import com.his.service.PlanService;

@RestController
@CrossOrigin
public class PlanRestController {

	private PlanService service;

	public PlanRestController(PlanService service) {
		super();
		this.service = service;
	}

	private Logger logger = LoggerFactory.getLogger(PlanRestController.class);

	@PostMapping("/registerplan")
	public ResponseEntity<String> addPlan(@RequestBody PlanDto plan) {
		boolean status = service.savePlan(plan);
		if (status) {
			logger.info("Plan saved successfully)...");
			return new ResponseEntity<>("plan added successfuly..", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Unable to Regster the plan", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/updateplan/{planId}/{activeSwitch}")
	public ResponseEntity<String> updateExistingPlan(@PathVariable("planId") Integer planId,
			@PathVariable("activeSwitch") String activeSwitch) {
		logger.info("Received request to update plan with ID: {} and active switch: {}", planId, activeSwitch);

		boolean status = service.updatePlan(planId, activeSwitch);
		if (status) {
			return new ResponseEntity<>("plan Updated...", HttpStatus.OK);
		} else {
			logger.error("Failed to update plan for ID: {}", planId);
			return new ResponseEntity<>("Update failed...", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("getplan/{planId}")
	public ResponseEntity<PlanDto> getPlan(@PathVariable("planId") Integer planId) {
		logger.info("Received request to get plan with ID : {}", planId);
		PlanDto plan = service.findByPlanId(planId);
		if (plan != null) {
			return new ResponseEntity<>(plan, HttpStatus.OK);
		} else {
			logger.warn("Plan not found for ID: {}", planId);
			return new ResponseEntity<>(plan, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("getplans")
	public ResponseEntity<List<PlanDto>> getAllPlans() {
		List<PlanDto> allPlans = service.findAllPlans();
		return new ResponseEntity<>(allPlans, HttpStatus.OK);
	}

}
