package com.his.feigns;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.his.dto.EDEntityDto;

@FeignClient("ED-API")
public interface EDFeign {

	@GetMapping("/getallplannames")
	public List<String> fetchPlanNames();

	@GetMapping("/getallplanstatuses")
	public List<String> fetchPlanStatuses();

	@PostMapping("/filter")
	public List<EDEntityDto> filterApplications(EDEntityDto example);

	@GetMapping("/getallapps")
	public List<EDEntityDto> fetchAllApplications();

}
