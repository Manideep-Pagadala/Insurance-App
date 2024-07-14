package com.his.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.his.dto.KIdsInfo;
import com.his.dto.Summary;
import com.his.entity.EducationData;
import com.his.entity.IncomeData;
import com.his.entity.KidsData;
import com.his.exception.CustomDCException;
import com.his.repo.EduRepo;
import com.his.repo.IncomeRepo;
import com.his.repo.KidRepo;

@Service
public class DCServiceImpl implements DCService {

	private IncomeRepo irepo;
	private EduRepo erepo;
	private KidRepo krepo;

	private static final Logger logger = LoggerFactory.getLogger(DCServiceImpl.class);

	public DCServiceImpl(IncomeRepo irepo, EduRepo erepo, KidRepo krepo) {
		this.irepo = irepo;
		this.erepo = erepo;
		this.krepo = krepo;
	}

	@Override
	public boolean saveIncome(IncomeData data) {
		return irepo.save(data).getIncomeId() != null;
	}

	@Override
	public boolean saveEducation(EducationData data) {
		return erepo.save(data).getEduId() != null;
	}

	@Override
	public String saveKids(KIdsInfo info) {
		List<KidsData> kids = info.getKids();
		try {
			if (!kids.isEmpty()) {
				krepo.saveAll(kids);
				return "Kids info saved.";
			} else {
				return "No kids data provided.";
			}
		} catch (Exception e) {
			logger.error("Error saving kids data", e);
			return "Failed to save kids data.";
		}
	}

	@Override
	public Summary getSummary(Integer appNumber) {
		try {
			IncomeData incomeData = irepo.findByAppNumber(appNumber);
			EducationData educationData = erepo.findByAppNumber(appNumber);
			List<KidsData> kids = krepo.findByAppNumber(appNumber);

			KIdsInfo kidsInfo = new KIdsInfo();
			kidsInfo.setKids(kids);

			if (incomeData == null && educationData == null)
				throw new CustomDCException(", Data Not Submitted....!");

			return new Summary(appNumber, incomeData, educationData, kidsInfo);

		} catch (Exception e) {
			throw new CustomDCException("Failed to Fetch Summary Data" + e.getMessage());
		}
	}

}
