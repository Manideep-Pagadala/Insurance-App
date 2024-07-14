package com.his.dto;

import com.his.entity.EducationData;
import com.his.entity.IncomeData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Summary {

	private Integer appNumber;

	private IncomeData incomeData;

	private EducationData educationData;

	private KIdsInfo kIdsInfo;

}
