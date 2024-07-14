package com.his.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
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
	@CreationTimestamp
	@Column(updatable  = false)
	private LocalDate createdDate;
	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDate updatedDate;
}
