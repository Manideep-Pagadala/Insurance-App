package com.his.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.his.entity.Plan;

public interface PlanRepo extends JpaRepository<Plan, Integer>{

	
}
