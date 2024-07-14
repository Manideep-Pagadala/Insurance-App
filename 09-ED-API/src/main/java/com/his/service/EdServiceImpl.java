package com.his.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.his.dto.ApplicationReg;
import com.his.dto.EDEntityDto;
import com.his.dto.Summary;
import com.his.entity.COEntity;
import com.his.entity.EDEntity;
import com.his.feigns.FeignAR;
import com.his.repo.CoRepo;
import com.his.repo.EdRepo;

@Service
public class EdServiceImpl implements EdService {

	private EdRepo erepo;

	private FeignAR feign;

	private CoRepo corepo;

	@Value("${dcModuleURL}")
	private String dcModuleURL;

	public EdServiceImpl(EdRepo erepo, FeignAR feign, CoRepo corepo) {
		this.erepo = erepo;
		this.feign = feign;
		this.corepo = corepo;
	}

	@Override
	public ApplicationReg getApplication(Integer appNumber) {
		return feign.getApplictionByAppNO(appNumber);
	}

	@Override
	public Summary getData(Integer appNumber) {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<Summary> forEntity = rt.getForEntity(dcModuleURL, Summary.class, appNumber);
		return forEntity.getBody();
	}

	@Override
	public EDEntity checkEleigibility(Integer appNumber) {

		EDEntity byAppNumber = erepo.findByAppNumber(appNumber);

		if (byAppNumber != null) {
			return byAppNumber;
		}

		ApplicationReg app = getApplication(appNumber);
		Summary data = getData(appNumber);

		if (app == null || data == null)
			return null;

		int planId = app.getPlanId();
		double salaryIncome = data.getIncomeData().getSalaryIncome();
		double propertyIncome = data.getIncomeData().getPropertyIncome();
		LocalDate today = LocalDate.now();
		int citizenAge = Period.between(app.getDob(), today).getYears();

		long eligibleKidsCount = data.getKIdsInfo().getKids().isEmpty() ? 0 :
	        data.getKIdsInfo().getKids().stream()
	                .filter(k -> Period.between(k.getKidDob(), today).getYears() <= 16)
	                .count();

		EDEntity ed = null;

		switch (planId) {
		case 1:
			ed = checkSnapEligibility(appNumber, salaryIncome);
			break;
		case 2:
			ed = checkCCAPEligibilty(appNumber, eligibleKidsCount, salaryIncome);
			break;
		case 3:
			ed = checkMedicaidEligibilty(appNumber, salaryIncome, propertyIncome);
			break;
		case 4:
			ed = checkMedicareEligiblity(appNumber, citizenAge);
			break;
		case 5:
			ed = checkRIWEligibility(appNumber, data, salaryIncome);
			break;
		default:
			System.out.println("Plan Id Invalid..!");
			break;
		}

		generateCorrespondance(appNumber);

		return erepo.save(ed);
	}

	private EDEntity checkRIWEligibility(Integer appNumber, Summary data, double salaryIncome) {
		EDEntity ed;
		if (salaryIncome == 0 && data.getEducationData().getEduId() != null) {
			ed = generateApprovedApplication(appNumber);
			ed.setPlanName("RIW");
		} else {
			ed = generateDenaialApplication(appNumber);
			ed.setPlanName("RIW");
			ed.setDenialReason(salaryIncome == 0 ? "Salary income not eligible" : "Graduation required");
		}
		return ed;
	}

	private EDEntity checkMedicareEligiblity(Integer appNumber, int citizenAge) {
		EDEntity ed;
		if (citizenAge >= 65) {
			ed = generateApprovedApplication(appNumber);
			ed.setPlanName("Medicare");
		} else {
			ed = generateDenaialApplication(appNumber);
			ed.setPlanName("Medicare");
			ed.setDenialReason("Age condition failed..");
		}
		return ed;
	}

	private EDEntity checkMedicaidEligibilty(Integer appNumber, double salaryIncome, double propertyIncome) {
		EDEntity ed;
		if (salaryIncome < 25000 && propertyIncome <= 0) {
			ed = generateApprovedApplication(appNumber);
			ed.setPlanName("Medicaid");
		} else {
			ed = generateDenaialApplication(appNumber);
			ed.setPlanName("Medicaid");
			ed.setDenialReason(salaryIncome <= 25000 ? "High salary income" : "High Property income");
		}
		return ed;
	}

	private EDEntity checkCCAPEligibilty(Integer appNumber, long eligibleKidsCount, double salaryIncome) {
		EDEntity ed;
		if (salaryIncome <= 25000 && eligibleKidsCount >= 1) {
			ed = generateApprovedApplication(appNumber);
			ed.setPlanName("CCAP");
		} else {
			ed = generateDenaialApplication(appNumber);
			ed.setPlanName("CCAP");
			ed.setDenialReason(eligibleKidsCount >= 1 ? "High salary income" : "kids condition failed");
		}
		return ed;
	}

	private EDEntity checkSnapEligibility(Integer appNumber, double salaryIncome) {
		EDEntity ed;
		if (salaryIncome <= 25000) {
			ed = generateApprovedApplication(appNumber);
			ed.setPlanName("SNAP");
		} else {
			ed = generateDenaialApplication(appNumber);
			ed.setPlanName("SNAP");
			ed.setDenialReason("Salaray income is high..!");
		}
		return ed;
	}

	private EDEntity generateApprovedApplication(Integer appNumber) {
		LocalDate today = LocalDate.now();
		return new EDEntity(0, "", "Approved", today.toString(), today.plusMonths(6).toString(), 25000.0, "NA",
				appNumber);
	}

	private EDEntity generateDenaialApplication(Integer appNumber) {
		return new EDEntity(0, "", "Denied", "NA", "NA", 0.0, "NA", appNumber);
	}

	private void generateCorrespondance(Integer appNumber) {
		COEntity coObj = new COEntity();
		coObj.setAppNumber(appNumber);
		coObj.setCoStatus("Pending");
		coObj.setCoGenDate(LocalDate.now());
		corepo.save(coObj);
	}

	@Override
	public List<EDEntity> fetchExpiringApplications(String eligEndDate) {
		return erepo.findByEligEndDate(LocalDate.parse(eligEndDate).minusDays(15).toString());
	}

	@Override
	public List<EDEntity> fetchAllApplications() {
		return erepo.findAll();
	}

	@Override
	public List<String> fetchAllPlanNames() {
		return erepo.getPlanNames();
	}

	@Override
	public List<String> fetchAllPlanStatuses() {
		return erepo.getPlanStatuses();
	}

	@Override
	public List<EDEntity> getAppsByFiltering(EDEntityDto dto) {
		EDEntity edEntity = new EDEntity();
		if (dto != null) {
			if (dto.getPlanName() != null && !dto.getPlanName().equals(""))
				edEntity.setPlanName(dto.getPlanName());

			if (dto.getEligStatus() != null && !dto.getEligStatus().equals(""))
				edEntity.setEligStatus(dto.getEligStatus());

			if (dto.getEligStartDate() != null && !dto.getEligStartDate().equals(""))
				edEntity.setEligStartDate(dto.getEligStartDate());

			if (dto.getEligEndDate() != null && !dto.getEligEndDate().equals(""))
				edEntity.setEligEndDate(dto.getEligEndDate());
		}
		Example<EDEntity> of = Example.of(edEntity);
		return erepo.findAll(of);
	}

	@Override
	public EDEntity getByAppNumber(Integer appNumber) {
		return erepo.findByAppNumber(appNumber);
	}

}
