package com.his.entity;

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
public class IncomeData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer incomeId;
	private Double salaryIncome;
	private Double propertyIncome;
	private Integer appNumber;	

}
