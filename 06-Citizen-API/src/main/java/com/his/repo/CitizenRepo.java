package com.his.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.his.entity.Citizen;

public interface CitizenRepo extends JpaRepository<Citizen, Integer> {

	Citizen findByCitizenEmail(String citizenEmail);

}
