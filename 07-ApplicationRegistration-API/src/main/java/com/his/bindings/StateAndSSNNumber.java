package com.his.bindings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StateAndSSNNumber {

	private String ssn;
	private String stateName;
}
