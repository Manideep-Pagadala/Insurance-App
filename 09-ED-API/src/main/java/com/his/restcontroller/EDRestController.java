package com.his.restcontroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.his.dto.EDEntityDto;
import com.his.entity.EDEntity;
import com.his.service.EdService;

@RestController
@CrossOrigin
public class EDRestController {

	private EdService service;

	public EDRestController(EdService service) {
		this.service = service;
	}

	@GetMapping("/ed/{appNumber}")
	public ResponseEntity<EDEntity> checkEligibility(@PathVariable("appNumber") Integer appNumber) {
		EDEntity obj = service.checkEleigibility(appNumber);
		System.err.println("Elig Method Hit , object:: " + obj);
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@GetMapping("/getExpApps/{eligEndDate}")
	public ResponseEntity<List<EDEntity>> getExpiringApplictaions(@PathVariable("eligEndDate") String eligEndDate) {
		List<EDEntity> list = service.fetchExpiringApplications(eligEndDate);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/getallapps")
	public ResponseEntity<List<EDEntity>> fetchAllApplications() {
		List<EDEntity> list = service.fetchAllApplications();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/getallplannames")
	public ResponseEntity<List<String>> fetchPlanNames() {
		List<String> list = service.fetchAllPlanNames();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/getallplanstatuses")
	public ResponseEntity<List<String>> fetchPlanStatuses() {
		List<String> list = service.fetchAllPlanStatuses();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/getbyappno/{appNumber}")
	public ResponseEntity<EDEntity> getByAppNumber(@PathVariable("appNumber") Integer appNumber) {
		EDEntity obj = service.getByAppNumber(appNumber);
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@PostMapping("/filter")
	public ResponseEntity<List<EDEntity>> filterApplications(@RequestBody EDEntityDto example) {
		List<EDEntity> list = service.getAppsByFiltering(example);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
}
