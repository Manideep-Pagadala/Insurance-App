package com.his.service;

import java.util.List;

import com.his.dto.Citizen;
import com.his.search.SearchCriteria;

import jakarta.servlet.http.HttpServletResponse;

public interface ReportsService {

	public List<Citizen> getCitizens(SearchCriteria sc);

	public void generateExcelReport(HttpServletResponse response);

	public void generatePDfReport(HttpServletResponse response) throws Exception;

	public List<String> getPlanNames();

	public List<String> getPlanStatus();

}
