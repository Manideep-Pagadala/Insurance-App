package com.his.binding;

import lombok.Data;

@Data
public class CitizenDto {

	private Integer citizenId;
	private String citizenName;
	private String citizenEmail;
	private String password;
	private Long phone;
	private String ssn;

}
