package com.kodlamaio.hrmns.hrmns.business.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetListJobDto {
	
	private int id;
		
	private int employerEmployerId;	

	private String jobPositon;

	private String jobInfo;

	private String cityInfo;

	private int minExpectedSalary;

	private int maxExpectedSalary;

	private int numberOfPositions;
	
	private LocalDate createDate;

	private LocalDate deadLine;

	private boolean status;


}
