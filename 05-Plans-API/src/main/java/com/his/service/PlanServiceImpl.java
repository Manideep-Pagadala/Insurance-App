package com.his.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.his.dto.PlanDto;
import com.his.entity.Plan;
import com.his.exception.PlansNotFoundException;
import com.his.repo.PlanRepo;

@Service
public class PlanServiceImpl implements PlanService {

	private PlanRepo repo;

	public PlanServiceImpl(PlanRepo repo) {
		this.repo = repo;
	}

	@Override
	public boolean savePlan(PlanDto dto) {
		Plan entity = new Plan();
		BeanUtils.copyProperties(dto, entity);
		entity.setActiveSwitch("Y");
		return repo.save(entity).getPlanId() != null;
	}

	@Override
	public PlanDto findByPlanId(Integer planId) {
		Plan plan = repo.findById(planId).orElseThrow(() -> {
			throw new PlansNotFoundException("Plan not found for the specified planId");
		});
		PlanDto planDto = new PlanDto();
		BeanUtils.copyProperties(plan, planDto);
		return planDto;
	}

	@Override
	public List<PlanDto> findAllPlans() {

		List<Plan> entities = repo.findAll();
		List<PlanDto> plans = new ArrayList<>();
		entities.forEach(e -> {
			PlanDto dto = new PlanDto();
			BeanUtils.copyProperties(e, dto);
			plans.add(dto);
		});

		if (plans.isEmpty())
			throw new PlansNotFoundException("Plans not found");

		return plans;
	}

	@Override
	public boolean updatePlan(Integer planId, String status) {
		Plan entity = repo.findById(planId).orElseThrow(() -> {
			throw new PlansNotFoundException("Plan not found for the specified planId");
		});
		entity.setActiveSwitch(status);
		Plan save = repo.save(entity);
		return save != null;
	}

}
