package com.his.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.his.entity.IncomeData;

public interface IncomeRepo extends JpaRepository<IncomeData, Serializable> {
	
IncomeData findByAppNumber(Integer appNumber);
}
