package com.his.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.his.entity.ApplicationReg;
import com.his.exception.MyInvalidApplicationException;
import com.his.feign.SSNAPIClient;
import com.his.repo.AppRegRepo;

@Service
public class AppRegServiceImpl implements AppRegService {

	private AppRegRepo repo;
	private SSNAPIClient feign;

	public AppRegServiceImpl(AppRegRepo repo, SSNAPIClient feign) {
		this.repo = repo;
		this.feign = feign;
	}

	@Value("${ssaWebApiURL}")
	private String ssaWebApURL;

	@Override
	public boolean createApplication(ApplicationReg regObj) {
		String state = findStateFromSSN(regObj.getSsn());
		if (!state.equalsIgnoreCase("Rhode-Island"))
			throw new MyInvalidApplicationException("Failed to create Application. You belong to " + state + ".");

		try {
			repo.save(regObj);
			return true;
		} catch (Exception e) {
			throw new MyInvalidApplicationException("Failed to create Application.");
		}
	}

	private String findStateFromSSN(String ssn) {
		return feign.getStateFromSSN(ssn).getBody();
	}

	@Override
	public ApplicationReg getApplication(Integer appNumber) {
		return repo.findById(appNumber).orElseThrow(
				() -> new MyInvalidApplicationException("Application Not Found for the specified App number"));
	}

	@Override
	public List<ApplicationReg> getApplications() {
		List<ApplicationReg> all = repo.findAll();
		if (all.isEmpty())
			throw new MyInvalidApplicationException("Applications List is Empty ");
		
		return all;
	}

	@Override
	public ApplicationReg getApplicationWithCitizenId(Integer citizenId) {
		ApplicationReg app = repo.findByCitizenId(citizenId);
		if (app == null)
			throw new MyInvalidApplicationException("Application Not Found for the specified citizenId");

		return app;
	}

}
