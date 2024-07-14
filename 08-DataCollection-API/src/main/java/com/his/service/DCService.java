package com.his.service;

import com.his.dto.KIdsInfo;
import com.his.dto.Summary;
import com.his.entity.EducationData;
import com.his.entity.IncomeData;

public interface DCService {

	public boolean saveIncome(IncomeData data);

	public boolean saveEducation(EducationData data);

	public String saveKids(KIdsInfo info);

	public Summary getSummary(Integer appNumber);
}
