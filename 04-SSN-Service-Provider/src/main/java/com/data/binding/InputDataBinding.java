package com.data.binding;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputDataBinding {

	private String name;
	private LocalDate dob;
	private String ssn;

}
