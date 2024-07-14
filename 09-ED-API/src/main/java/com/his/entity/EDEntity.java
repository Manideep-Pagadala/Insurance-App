package com.his.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class EDEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer eligId;
	private String planName;
	private String eligStatus;
	private String eligStartDate;
	private String eligEndDate;
	private Double benfitAmount;
	private String denialReason;
	private Integer appNumber;

}
