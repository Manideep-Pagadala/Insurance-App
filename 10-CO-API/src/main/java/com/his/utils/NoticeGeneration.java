package com.his.utils;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.his.dto.ApplicationReg;
import com.his.dto.EDEntityDto;
import com.his.feigns.FeignAR;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component
public class NoticeGeneration {
	private EmailUtils emailUtils;

	private FeignAR arFeign;

	public NoticeGeneration(EmailUtils emailUtils, FeignAR arFeign) {
		this.emailUtils = emailUtils;
		this.arFeign = arFeign;
	}

	public void generateApprovedNotice(EDEntityDto edData, File file, ApplicationReg appData) throws IOException {

		FileOutputStream fos = new FileOutputStream(file);
		Document document = new Document(PageSize.A4);

		PdfWriter.getInstance(document, fos);
		document.open();

		Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN, 20, Font.BOLD, Color.GREEN);
		Paragraph title = new Paragraph("Eligibility Approved Notice", fontTitle);
		title.setAlignment(Element.ALIGN_CENTER);
		title.setSpacingAfter(20);
		document.add(title);

		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 3, 7 });
		table.setSpacingBefore(10);
		table.setSpacingAfter(15);

		Font cellFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, Color.BLACK);
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.LIGHT_GRAY);
		cell.setPadding(5);

		cell.setPhrase(new Phrase("App Number", cellFont));
		table.addCell(cell);
		table.addCell(String.valueOf(appData.getAppNumber()));

		cell.setPhrase(new Phrase("Citizen Name", cellFont));
		table.addCell(cell);
		table.addCell(String.valueOf(appData.getCitizenName()));

		cell.setPhrase(new Phrase("Plan Name", cellFont));
		table.addCell(cell);
		table.addCell(edData.getPlanName());

		cell.setPhrase(new Phrase("Plan Status", cellFont));
		table.addCell(cell);
		table.addCell(edData.getEligStatus());

		cell.setPhrase(new Phrase("Start Date", cellFont));
		table.addCell(cell);
		table.addCell(edData.getEligStartDate());

		cell.setPhrase(new Phrase("End Date", cellFont));
		table.addCell(cell);
		table.addCell(edData.getEligEndDate());

		cell.setPhrase(new Phrase("Benefit Amount", cellFont));
		table.addCell(cell);
		table.addCell(String.valueOf(edData.getBenfitAmount()));

		document.add(table);
		document.close();
		fos.close();

	}

	public void generateDeniedNotice(EDEntityDto edData, File file, ApplicationReg appData) throws IOException {

		FileOutputStream fos = new FileOutputStream(file);
		Document document = new Document(PageSize.A4);

		PdfWriter.getInstance(document, fos);
		document.open();

		Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN, 20, Font.BOLD, Color.RED);

		Paragraph title = new Paragraph("Eligibility Denied Notice", fontTitle);
		title.setAlignment(Element.ALIGN_CENTER);
		title.setSpacingAfter(15);
		document.add(title);

		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 3, 7 });
		table.setSpacingBefore(10);
		table.setSpacingAfter(20);

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.lightGray);
		cell.setPadding(5);

		Font cellFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, Color.BLACK);

		cell.setPhrase(new Phrase("App Number", cellFont));
		table.addCell(cell);
		table.addCell(String.valueOf(appData.getAppNumber()));

		cell.setPhrase(new Phrase("Citizen Name", cellFont));
		table.addCell(cell);
		table.addCell(String.valueOf(appData.getCitizenName()));

		cell.setPhrase(new Phrase("Plan Name", cellFont));
		table.addCell(cell);
		table.addCell(edData.getPlanName());

		cell.setPhrase(new Phrase("Plan Status", cellFont));
		table.addCell(cell);
		table.addCell(edData.getEligStatus());

		cell.setPhrase(new Phrase("Denial Reason", cellFont));
		table.addCell(cell);
		table.addCell(edData.getDenialReason());

		document.add(table);
		document.close();
		fos.close();
	}

	public void generateExpringNotice(EDEntityDto edData) throws IOException {
		ApplicationReg appData = arFeign.getApplictionByAppNO(edData.getAppNumber());

		File noticePdf = new File(edData.getAppNumber() + ".pdf");
		FileOutputStream fos = new FileOutputStream(noticePdf);
		Document document = new Document(PageSize.A4);

		PdfWriter.getInstance(document, fos);
		document.open();

		Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN, 20, Font.BOLD, Color.RED);
		Paragraph title = new Paragraph("Remainder for expiring plan...", fontTitle);
		title.setAlignment(Element.ALIGN_CENTER);
		title.setSpacingAfter(5);
		document.add(title);

		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 3, 7 });
		table.setSpacingBefore(10);
		table.setSpacingAfter(15);

		Font cellFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, Color.BLACK);
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.LIGHT_GRAY);
		cell.setPadding(5);

		cell.setPhrase(new Phrase("App Number", cellFont));
		table.addCell(cell);
		table.addCell(String.valueOf(edData.getAppNumber()));

		cell.setPhrase(new Phrase("Citizen Name", cellFont));
		table.addCell(cell);
		table.addCell(String.valueOf(appData.getCitizenName()));

		cell.setPhrase(new Phrase("Plan Name", cellFont));
		table.addCell(cell);
		table.addCell(edData.getPlanName());

		cell.setPhrase(new Phrase("Plan Status", cellFont));
		table.addCell(cell);
		table.addCell("Expiring in 15 days..");

		cell.setPhrase(new Phrase("Start Date", cellFont));
		table.addCell(cell);
		table.addCell(edData.getEligStartDate());

		cell.setPhrase(new Phrase("End Date", cellFont));
		table.addCell(cell);
		table.addCell(edData.getEligEndDate());

		document.add(table);
		document.close();
		fos.close();

		String sub = "Urgent Notice: Your Benefit Amount Plan Expires in 15 Days";

		String body = "<html>" + "<body>" + "<p>Dear " + appData.getCitizenName() + ",</p>"
				+ "<p>We hope this email finds you well.</p>"
				+ "<p>We would like to inform you that your benefit amount plan is set to expire in just 15 days. "
				+ "To ensure uninterrupted access to your benefits, we kindly request your attention to renew your plan before the expiration date.</p>"
				+ "<p>Below are the details of your benefit amount plan:</p>" + "<ul>"
				+ "<li><b>Application Number:</b>" + edData.getAppNumber() + "</li>"
				+ "<li ><b>Plan Expiring Date:</b><font color='red'>  " + edData.getEligEndDate() + "</font></li>"
				+ "</ul>" + "<p>Best regards,<br>" + "Manideep <br>" + "RI GOV<br>" + "</body>" + "</html>";

		emailUtils.sendEmail(sub, body, appData.getGmail(), noticePdf);

	}

}
