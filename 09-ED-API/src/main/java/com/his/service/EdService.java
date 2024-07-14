package com.his.service;

import java.util.List;

import com.his.dto.ApplicationReg;
import com.his.dto.EDEntityDto;
import com.his.dto.Summary;
import com.his.entity.EDEntity;

public interface EdService {

	ApplicationReg getApplication(Integer appNumber);

	Summary getData(Integer appNumber);

	EDEntity checkEleigibility(Integer appNumber);

	EDEntity getByAppNumber(Integer appNumber);

	List<EDEntity> fetchExpiringApplications(String eligEndDate);

	List<EDEntity> fetchAllApplications();

	List<String> fetchAllPlanNames();

	List<String> fetchAllPlanStatuses();

	List<EDEntity> getAppsByFiltering(EDEntityDto example);

}
