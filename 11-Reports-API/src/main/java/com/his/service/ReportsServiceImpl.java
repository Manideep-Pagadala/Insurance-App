package com.his.service;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.his.dto.ApplicationReg;
import com.his.dto.Citizen;
import com.his.dto.EDEntityDto;
import com.his.feigns.ARFeign;
import com.his.feigns.EDFeign;
import com.his.search.SearchCriteria;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportsServiceImpl implements ReportsService {

//	private CitizenFeign citizenFeign;

	private EDFeign edFeign;

	private ARFeign aRFeign;

	private List<String> planNames;

	private List<String> planStatuses;

	public ReportsServiceImpl(EDFeign edFeign, ARFeign aRFeign) {
		this.edFeign = edFeign;
		this.aRFeign = aRFeign;
	}

	@Override
	public List<Citizen> getCitizens(SearchCriteria sc) {

		EDEntityDto dto = new EDEntityDto();
		List<Citizen> citizensList = new ArrayList<>();

		if (sc != null) {
			if (!sc.getPlanName().equals(""))
				dto.setPlanName(sc.getPlanName());

			if (!sc.getPlanStatus().equals(""))
				dto.setEligStatus(sc.getPlanStatus());

			if (!sc.getStartDate().equals(""))
				dto.setEligStartDate(sc.getStartDate());

			if (!sc.getEndDate().equals(""))
				dto.setEligEndDate(sc.getEndDate());
		}

		try {
			List<EDEntityDto> filterApplications = edFeign.filterApplications(dto);
			filterApplications.forEach((ed) -> {
				ApplicationReg app = aRFeign.getApplictionByAppNO(ed.getAppNumber());
				Citizen citizen = new Citizen();
				BeanUtils.copyProperties(app, citizen);
				citizensList.add(citizen);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

		return citizensList;
	}

	@Override
	public void generateExcelReport(HttpServletResponse response) {

		try (ServletOutputStream outputStream = response.getOutputStream();
				XSSFWorkbook workbook = new XSSFWorkbook()) {

			XSSFSheet sheet = workbook.createSheet("report");
			XSSFRow headersRow = sheet.createRow(0);
			headersRow.createCell(0).setCellValue("Citizen Name");
			headersRow.createCell(1).setCellValue("Plan Name");
			headersRow.createCell(2).setCellValue("Plan Status");
			headersRow.createCell(3).setCellValue("Elig Start Date");
			headersRow.createCell(4).setCellValue("Elig End Date");
			headersRow.createCell(5).setCellValue("Benefit Amount");
			headersRow.createCell(6).setCellValue("Denial Reason");

			List<EDEntityDto> list = edFeign.fetchAllApplications();
			int rowNum = 1;
			for (EDEntityDto ed : list) {
				ApplicationReg ar = aRFeign.getApplictionByAppNO(ed.getAppNumber());
				XSSFRow row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(ar.getCitizenName());
				row.createCell(1).setCellValue(ed.getPlanName());
				row.createCell(2).setCellValue(ed.getEligStatus());
				row.createCell(3).setCellValue(ed.getEligStartDate());
				row.createCell(4).setCellValue(ed.getEligEndDate());
				row.createCell(5).setCellValue(ed.getBenfitAmount().toString());
				row.createCell(6).setCellValue(ed.getDenialReason());
			}
			workbook.write(outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void generatePDfReport(HttpServletResponse response) throws Exception {
		try (ServletOutputStream outputStream = response.getOutputStream();
				Document pdfDoc = new Document(PageSize.A4)) {

			PdfWriter.getInstance(pdfDoc, outputStream);
			pdfDoc.open();

			PdfPTable table = new PdfPTable(7);
			table.setWidthPercentage(100);

			Font headerFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, Color.BLACK);
			Color headerBackgroundColor = Color.LIGHT_GRAY;

			addHeaderCell(table, "Citizen Name", headerFont, headerBackgroundColor);
			addHeaderCell(table, "Plan Name", headerFont, headerBackgroundColor);
			addHeaderCell(table, "Plan Status", headerFont, headerBackgroundColor);
			addHeaderCell(table, "Elig Start Date", headerFont, headerBackgroundColor);
			addHeaderCell(table, "Elig End Date", headerFont, headerBackgroundColor);
			addHeaderCell(table, "Benefit Amount", headerFont, headerBackgroundColor);
			addHeaderCell(table, "Denial Reason", headerFont, headerBackgroundColor);

			List<EDEntityDto> list = edFeign.fetchAllApplications();
			for (EDEntityDto ed : list) {
				ApplicationReg ar = aRFeign.getApplictionByAppNO(ed.getAppNumber());
				table.addCell(ar.getCitizenName());
				table.addCell(ed.getPlanName());
				table.addCell(ed.getEligStatus());
				table.addCell(ed.getEligStartDate());
				table.addCell(ed.getEligEndDate());
				table.addCell(ed.getBenfitAmount().toString());
				table.addCell(ed.getDenialReason());
			}

			pdfDoc.add(table);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<String> getPlanNames() {
		if (planNames == null)
			planNames = edFeign.fetchPlanNames();
		return planNames;
	}

	@Override
	public List<String> getPlanStatus() {
		if (planStatuses == null)
			planStatuses = edFeign.fetchPlanStatuses();
		return planStatuses;
	}

	private void addHeaderCell(PdfPTable table, String headerText, Font headerFont, Color backgroundColor) {
		PdfPCell headerCell = new PdfPCell(new Phrase(headerText, headerFont));
		headerCell.setBackgroundColor(backgroundColor);
		headerCell.setPadding(5);
		headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(headerCell);
	}
}
