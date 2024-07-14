package com.his.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "SSN-SERVICE-PROVIDER")
public interface SSNAPIClient {

	@GetMapping("/get/{ssn}")
	public ResponseEntity<String> getStateFromSSN(@PathVariable String ssn);

}
