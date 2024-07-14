package com.his.search;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchCriteria {

	private String planName;
	private String planStatus;
	private String startDate;
	private String endDate;

}
