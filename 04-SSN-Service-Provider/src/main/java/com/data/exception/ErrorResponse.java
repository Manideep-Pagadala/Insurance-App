package com.data.exception;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorResponse {

	public String id;
	public String msg;
	public LocalDateTime time;

}
