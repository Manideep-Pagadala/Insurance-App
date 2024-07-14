package com.his.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Citizen {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer citizenId;
	private String citizenName;
	private String citizenEmail;
	private String password;
	private Long phone;
	private String isPwdUpdated;
	private String role;
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDate createdDate;
	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDate updatedDate;
}
