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
public class KidsData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer kidId;
	private String kidName;
	private LocalDate kidDob;
	private String kidSSN;
	private Integer appNumber;

}
