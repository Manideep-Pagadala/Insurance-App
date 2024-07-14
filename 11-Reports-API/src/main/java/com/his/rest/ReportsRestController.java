package com.his.rest;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.his.dto.Citizen;
import com.his.search.SearchCriteria;
import com.his.service.ReportsService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
public class ReportsRestController {
	private ReportsService service;

	public ReportsRestController(ReportsService service) {
		this.service = service;
	}

	@PostMapping("citizens")
	public List<Citizen> postMethodName(@RequestBody SearchCriteria sc) {
		return service.getCitizens(sc);
	}

	@GetMapping("/plannames")
	public List<String> fetchPlanNames() {
		return service.getPlanNames();
	}

	@GetMapping("/plan-statuses")
	public List<String> fetchPlanStatuses() {
		return service.getPlanStatus();
	}

	@GetMapping("excel")
	public void generateExcel(HttpServletResponse response) {

		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=data.xlsx";
		response.addHeader(headerKey, headerValue);
		service.generateExcelReport(response);
	}

	@GetMapping("/pdf")
	public void generatePDF(HttpServletResponse response) {
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=data.pdf");
		try {
			service.generatePDfReport(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
