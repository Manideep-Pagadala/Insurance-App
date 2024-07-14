package com.his.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.his.entity.ApplicationReg;

public interface AppRegRepo extends JpaRepository<ApplicationReg, Serializable> {

	public ApplicationReg findByCitizenId(Integer citizenId);
}
