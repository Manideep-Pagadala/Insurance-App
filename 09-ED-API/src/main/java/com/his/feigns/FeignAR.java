package com.his.feigns;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.his.dto.ApplicationReg;

@FeignClient(name = "AR-API")
public interface FeignAR {

	@GetMapping("/getbyappno/{appNumber}")
	public ApplicationReg getApplictionByAppNO(@PathVariable("appNumber") Integer appNumber);

	
}
