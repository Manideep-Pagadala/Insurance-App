package com.data.service;

import com.data.binding.InputDataBinding;
import com.data.binding.StateAndSSNNumber;

public interface MyService {

	public StateAndSSNNumber getStates(InputDataBinding bindingObj);
	
	public String getStateBySSN(String ssn);

}
