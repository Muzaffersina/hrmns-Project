package com.kodlamaio.hrmns.hrmns.business.requests.update;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateJobPositionRequest {

	@NotNull
	@Positive
	private short id;

	@NotNull
	@Size(min = 1)
	private String name;
}
