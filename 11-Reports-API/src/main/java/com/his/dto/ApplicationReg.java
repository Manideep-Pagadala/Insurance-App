package com.his.dto;

import java.time.LocalDate;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class ApplicationReg {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer appNumber;
	private String citizenName;
	private LocalDate dob;
	private String gender;
	private String gmail;
	private String ssn;
	private Integer planId;
	private Integer citizenId;
	private LocalDate createdDate;
	private LocalDate updatedDate;

}
