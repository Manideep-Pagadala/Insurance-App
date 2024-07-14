package com.his.dto;

import lombok.Data;

@Data
public class EDEntityDto {

	private Integer eligId;
	private String planName;
	private String eligStatus;
	private String eligStartDate;
	private String eligEndDate;
	private Double benfitAmount;
	private String denialReason;
	private Integer appNumber;

}
