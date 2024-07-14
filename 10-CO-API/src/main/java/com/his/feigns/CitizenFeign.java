package com.his.feigns;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.his.dto.EDEntityDto;

@FeignClient("CITIZEN-API")
public interface CitizenFeign {
	
	@GetMapping("/ed/{appNumber}")
	public ResponseEntity<EDEntityDto> checkEligibility(@PathVariable("appNumber") Integer appNumber);

}
