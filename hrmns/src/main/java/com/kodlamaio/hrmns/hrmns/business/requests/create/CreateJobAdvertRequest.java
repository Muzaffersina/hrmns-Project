package com.kodlamaio.hrmns.hrmns.business.requests.create;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobAdvertRequest {

	@NotNull
	@Positive
	private int employerEmployerId;
	
	@NotNull
	@Positive
	private short jobPositionId;
	
	@NotNull
	@Positive
	private short cityId;
	
	@NotNull
	@Positive
	private short workingTypeId;
	
	@NotNull
	@Positive
	private short workingTimeId;

	@NotNull
	private String jobInfo;

	@NotNull
	@Positive
	private int minExpectedSalary;

	@NotNull
	@Positive
	private int maxExpectedSalary;

	@NotNull
	@Positive
	private int numberOfPositions;

	@NotNull
	private LocalDate deadLine;
}
