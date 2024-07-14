package com.his.service;

import java.util.List;

import com.his.entity.ApplicationReg;

public interface AppRegService {

	// public StateAndSSNNumber valiadateSSN(WebRequestBindingClass bindingObj);
	
	public boolean createApplication(ApplicationReg regObj);

	public ApplicationReg getApplication(Integer appNumber);
	
	public ApplicationReg getApplicationWithCitizenId(Integer citizenId);

	public List<ApplicationReg> getApplications();
	
	

}
