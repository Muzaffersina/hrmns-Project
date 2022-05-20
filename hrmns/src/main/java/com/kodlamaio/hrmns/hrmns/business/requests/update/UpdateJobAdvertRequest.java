package com.kodlamaio.hrmns.hrmns.business.requests.update;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateJobAdvertRequest {

	@NotNull
	@Positive
	private int id;

	@NotNull
	private int employerEmployerId;

	@NotNull
	private short jobPositonId;

	@NotNull
	private short cityId;

	@NotNull
	private short workingTypeId;

	@NotNull
	private short workingTimeId;

	@NotNull
	private String jobInfo;

	@NotNull
	private int minExpectedSalary;

	@NotNull
	private int maxExpectedSalary;

	@NotNull
	private int numberOfPositions;

	@NotNull
	private LocalDate createDate;

	@NotNull
	private LocalDate deadLine;
}
