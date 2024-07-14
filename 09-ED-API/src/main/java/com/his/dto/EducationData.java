package com.his.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class EducationData {

	private Integer eduId;
	private String highestDegree;
	private LocalDate graduationYear;
	private String universityName;
	private Integer appNumber;
}
