package com.his.service;

import java.util.List;

import com.his.binding.CitizenDto;
import com.his.entity.Citizen;

public interface CitizenService {

	public String saveCitizen(CitizenDto citizen);

	public Citizen getById(Integer citizenId);

	public List<CitizenDto> getAll();

	public void deleteByID(Integer citizenId);

	boolean updateCitizen(CitizenDto citizen);

}
