package com.his.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer eduId;
	private String highestDegree;
	private LocalDate graduationYear;
	private String universityName;
	private Integer appNumber;	
}
