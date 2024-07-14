package com.his.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.his.entity.EDEntity;
import java.util.List;

public interface EdRepo extends JpaRepository<EDEntity, Serializable> {

	EDEntity findByAppNumber(Integer appNumber);

	List<EDEntity> findByEligEndDate(String eligEndDate);

	@Query("select distinct(planName) from EDEntity")
	public List<String> getPlanNames();

	@Query("select distinct(eligStatus) from EDEntity")
	public List<String> getPlanStatuses();

}
