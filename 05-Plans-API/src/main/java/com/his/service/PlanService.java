package com.his.service;

import java.util.List;

import com.his.dto.PlanDto;

public interface PlanService {

	public boolean savePlan(PlanDto dto);

	public PlanDto findByPlanId(Integer planId);

	public List<PlanDto> findAllPlans();

	public boolean updatePlan(Integer planId, String status);
}
