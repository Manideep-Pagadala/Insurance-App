package com.his.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.his.entity.EducationData;

public interface EduRepo extends JpaRepository<EducationData, Serializable> {
	
	EducationData findByAppNumber(Integer appNumber);
}
