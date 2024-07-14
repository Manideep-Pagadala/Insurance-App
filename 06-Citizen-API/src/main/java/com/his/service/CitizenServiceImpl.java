package com.his.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.his.binding.CitizenDto;
import com.his.entity.Citizen;
import com.his.exception.CitizenNotFoundException;
import com.his.repo.CitizenRepo;

@Service
public class CitizenServiceImpl implements CitizenService {

	private CitizenRepo repo;

	public CitizenServiceImpl(CitizenRepo repo) {
		this.repo = repo;
	}

	@Override
	public String saveCitizen(CitizenDto citizen) {
		Citizen byCitizenEmail = repo.findByCitizenEmail(citizen.getCitizenEmail());
		if (byCitizenEmail != null)
			return "Email should be unique";

		Citizen citizenEntity = new Citizen();
		BeanUtils.copyProperties(citizen, citizenEntity);
		citizenEntity.setIsPwdUpdated("NO");
		citizenEntity.setRole("1");
		Citizen obj = repo.save(citizenEntity);

		return obj != null ? "Registration successful..." : "Registration Failed...";
	}

	@Override
	public boolean updateCitizen(CitizenDto citizen) {
		Citizen citizenEntity = repo.findById(citizen.getCitizenId()).orElseThrow(() -> {
			throw new CitizenNotFoundException("Citizen Not Found..!");
		});
		BeanUtils.copyProperties(citizen, citizenEntity);
		citizenEntity.setIsPwdUpdated("NO");
		citizenEntity.setRole("1");
		return repo.save(citizenEntity) != null;
	}

	@Override
	public Citizen getById(Integer citizenId) {
		return repo.findById(citizenId).orElseThrow(() -> {
			throw new CitizenNotFoundException("Citizen Not Found..!");
		});
	}

	@Override
	public List<CitizenDto> getAll() {
		List<Citizen> list = repo.findAll();
		List<CitizenDto> citizenList = list.stream().map(e -> {
			CitizenDto citizenDto = new CitizenDto();
			BeanUtils.copyProperties(e, citizenDto);
			return citizenDto;
		}).toList();
		if (citizenList.isEmpty())
			throw new CitizenNotFoundException("Citizens List is Empty...!");
		
		return citizenList;
	}

	@Override
	public void deleteByID(Integer citizenId) {
		Citizen byId = getById(citizenId);
		repo.delete(byId);
	}

}
