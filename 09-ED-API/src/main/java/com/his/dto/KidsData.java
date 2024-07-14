package com.his.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class KidsData {

	private Integer kidId;
	private String kidName;
	private LocalDate kidDob;
	private String kidSSN;
	private Integer appNumber;

}
