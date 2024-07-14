package com.his.dto;

import java.util.List;

import lombok.Data;

@Data
public class KIdsInfo {

	private Integer appNumber;
	
	private List<KidsData> kids;
}
