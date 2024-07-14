package com.data.service;

import org.springframework.stereotype.Service;

import com.data.binding.InputDataBinding;
import com.data.binding.StateAndSSNNumber;
import com.data.exception.SSNNotFoundException;

@Service
public class MyServiceImpl implements MyService {

	@Override
	public StateAndSSNNumber getStates(InputDataBinding bindingObj) {
		String ssnNumber = bindingObj.getSsn();
		if (ssnNumber != null) {
			switch (ssnNumber.charAt(0)) {
			case '1':
				return new StateAndSSNNumber(ssnNumber, "New Jersey");
			case '2':
				return new StateAndSSNNumber(ssnNumber, "Texas");
			case '3':
				return new StateAndSSNNumber(ssnNumber, "Florida");
			case '4':
				return new StateAndSSNNumber(ssnNumber, "Tampa");
			case '5':
				return new StateAndSSNNumber(ssnNumber, "Rhode-Island");
			case '6':
				return new StateAndSSNNumber(ssnNumber, "California");

			default:
				throw new SSNNotFoundException("Invalid SSN Number...");
			}
		} else {
			throw new SSNNotFoundException("Invalid SSN Number...");
		}
	}

	@Override
	public String getStateBySSN(String ssnNumber) {

		if (ssnNumber != null) {
			switch (ssnNumber.charAt(0)) {
			case '1':
				return "New Jersey";
			case '2':
				return "Texas";
			case '3':
				return "Florida";
			case '4':
				return "Texas";
			case '5':
				return "Rhode-Island";
			case '6':
				return "California";

			default:
				throw new SSNNotFoundException("Invalid SSN Number...");
			}
		} else {
			throw new SSNNotFoundException("Invalid SSN Number...");
		}
	}

}
