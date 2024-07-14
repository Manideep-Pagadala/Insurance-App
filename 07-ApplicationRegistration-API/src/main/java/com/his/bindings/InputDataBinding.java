package com.his.bindings;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputDataBinding {

	private String name;
	private LocalDate dob;
	private String ssn;

}
