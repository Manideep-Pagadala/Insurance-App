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

import com.his.entity.ApplicationReg;
import com.his.service.AppRegService;

@RestController
@CrossOrigin
public class AppRegRestController {

	private AppRegService service;

	public AppRegRestController(AppRegService service) {
		this.service = service;
	}

	@PostMapping("/save")
	public ResponseEntity<String> saveApplication(@RequestBody ApplicationReg bindingObj) {
		boolean status = service.createApplication(bindingObj);
		String message = status ? "Application Created." : "Failed to create Application.";
		HttpStatus httpStatus = status ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
		return new ResponseEntity<>(message, httpStatus);
	}

	@GetMapping("/getbyappno/{appNumber}")
	public ResponseEntity<ApplicationReg> getApplictionByAppNumber(@PathVariable("appNumber") Integer appNumber) {
		ApplicationReg application = service.getApplication(appNumber);
		return new ResponseEntity<>(application, HttpStatus.OK);
	}

	@GetMapping("/getbycid/{citizenId}")
	public ResponseEntity<ApplicationReg> getApplictionByCitizenId(@PathVariable("citizenId") Integer citizenId) {
		ApplicationReg application = service.getApplicationWithCitizenId(citizenId);
		return new ResponseEntity<>(application, HttpStatus.OK);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<ApplicationReg>> getAllApplictions() {
		List<ApplicationReg> applications = service.getApplications();
		return new ResponseEntity<>(applications, HttpStatus.OK);

	}

}
