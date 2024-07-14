package com.his.restcontroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.his.binding.CitizenDto;
import com.his.entity.Citizen;
import com.his.service.CitizenService;

@RestController
@CrossOrigin
public class CitizenRestController {
	
	private CitizenService service;

	public CitizenRestController(CitizenService service) {
		this.service = service;
	}

	@PostMapping("/registercitizen")
	public ResponseEntity<String> addCitizen(@RequestBody CitizenDto citizen) {
		String status = service.saveCitizen(citizen);

		return status.equals("Registration successful...")
				? new ResponseEntity<>("Citizen Registration successfull..", HttpStatus.OK)
				: new ResponseEntity<>("Registration Failed..!", HttpStatus.NOT_FOUND);
	}

	@PutMapping("/update")
	public ResponseEntity<String> updateCitizen(@RequestBody CitizenDto citizen) {
		boolean status = service.updateCitizen(citizen);
		return status ? new ResponseEntity<>("Deatils Updated..", HttpStatus.OK)
				: new ResponseEntity<>("Unable to update.. ", HttpStatus.NOT_FOUND);
	}

	@GetMapping("getcitizen")
	public ResponseEntity<Citizen> getCitizen(@RequestParam("citizenId") Integer citizenId) {
		Citizen byId = service.getById(citizenId);
		if (byId != null)
			return new ResponseEntity<>(byId, HttpStatus.OK);
		else
			return new ResponseEntity<>(byId, HttpStatus.NOT_FOUND);
	}

	@GetMapping("getallcitizens")
	public ResponseEntity<List<CitizenDto>> getCitizens() {
		List<CitizenDto> allCitizens = service.getAll();
		return new ResponseEntity<>(allCitizens, HttpStatus.OK);
	}

	@DeleteMapping("deletecitizen")
	public ResponseEntity<String> removeCitizen(@RequestParam("citizenId") Integer citizenId) {
		service.deleteByID(citizenId);
		return new ResponseEntity<>("Account Deleted successfully...", HttpStatus.OK);
	}

}
