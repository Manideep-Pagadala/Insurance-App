package com.his.bindings;

import java.time.LocalDate;

import lombok.Data;

@Data
public class WebRequestBindingClass {

	private String citizenName;
	private LocalDate dob;
	private String ssn;
	private String planName;

}
