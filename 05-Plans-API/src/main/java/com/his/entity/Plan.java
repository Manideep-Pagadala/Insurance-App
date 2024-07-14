package com.his.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Plan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer planId;
	private String planName;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	public String activeSwitch;
	private String comments;
	@CreationTimestamp
	private LocalDate createdDate;
	@UpdateTimestamp
	private LocalDate updatedDate;
	private Integer createdBy;
	private Integer updatedBy;

}
