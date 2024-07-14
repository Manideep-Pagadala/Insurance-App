package com.his.feigns;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.his.dto.EDEntityDto;

@FeignClient("ED-API")
public interface EDFeign {

	@GetMapping("/ed/{appNumber}")
	public EDEntityDto checkEligibility(@PathVariable("appNumber") Integer appNumber);

	@GetMapping("/getExpApps/{eligEndDate}")
	public List<EDEntityDto> getExpiringApplictaions(@PathVariable("eligEndDate") String eligEndDate);

	@GetMapping("/getallapps")
	public List<EDEntityDto> fetchAllApplications();

}
