package com.kodlamaio.hrmns.hrmns.business.requests.create;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobRequest {
	
	@NotNull
	private int employerEmployerId;
	
	@NotNull
	private String jobPositon;
	
	@NotNull
	private String jobInfo;
	
	@NotNull
	private String cityInfo;
	
	@NotNull
	private int minExpectedSalary;
	
	@NotNull
	private int maxExpectedSalary;
	
	@NotNull
	private int numberOfPositions;
	
	@NotNull
	private LocalDate deadLine;
}
