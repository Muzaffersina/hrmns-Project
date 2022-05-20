package com.kodlamaio.hrmns.hrmns.business.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetListJobAdvertDto {
	
	private int id;
		
	private int employerEmployerId;	

	private short jobPositonId;
	
	private short cityId;
	
	private short workingTypeId;
	
	private short workingTimeId;

	private String jobInfo;

	private int minExpectedSalary;

	private int maxExpectedSalary;

	private int numberOfPositions;
	
	private LocalDate createDate;

	private LocalDate deadLine;

	private boolean status;


}
