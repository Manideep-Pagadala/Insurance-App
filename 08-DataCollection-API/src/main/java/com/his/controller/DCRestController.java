package com.his.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.his.dto.KIdsInfo;
import com.his.dto.Summary;
import com.his.entity.EducationData;
import com.his.entity.IncomeData;
import com.his.service.DCService;

@RestController
@CrossOrigin
public class DCRestController {

	private DCService service;

	public DCRestController(DCService service) {
		this.service = service;
	}

	@PostMapping("/income")
	public ResponseEntity<String> saveIncomeData(@RequestBody IncomeData data) {
		boolean status = service.saveIncome(data);
		return status ? new ResponseEntity<>("IncomeData saved..", HttpStatus.CREATED)
				: new ResponseEntity<>("Failed to save IncomeData..!", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("/education")
	public ResponseEntity<String> saveEducationData(@RequestBody EducationData data) {
		boolean status = service.saveEducation(data);
		return status ? new ResponseEntity<>("EducationData saved..", HttpStatus.CREATED)
				: new ResponseEntity<>("Failed to save EducationData..!", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("/kids")
	public ResponseEntity<String> saveKidsData(@RequestBody KIdsInfo data) {
		String message = service.saveKids(data);
		HttpStatus status = message.equalsIgnoreCase("Failed to save kids data.") ? HttpStatus.INTERNAL_SERVER_ERROR
				: HttpStatus.CREATED;
		return new ResponseEntity<>(message, status);
	}

	@GetMapping("/summary/{appNumber}")
	public ResponseEntity<Summary> getSummary(@PathVariable("appNumber") Integer appNumber) {
		Summary data = service.getSummary(appNumber);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

}
