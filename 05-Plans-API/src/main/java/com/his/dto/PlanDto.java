package com.his.dto;

import java.time.LocalDate;

import lombok.Data;
@Data
public class PlanDto {

	private Integer planId;
	private String planName;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	public String activeSwitch;
	private String comments;
}
