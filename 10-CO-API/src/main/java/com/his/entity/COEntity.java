package com.his.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class COEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer coNoticeId;
	private LocalDate coGenDate;
	private String coStatus;
	private LocalDate coPrintDate;
	private String cS3Url;
	private Integer appNumber;

}
