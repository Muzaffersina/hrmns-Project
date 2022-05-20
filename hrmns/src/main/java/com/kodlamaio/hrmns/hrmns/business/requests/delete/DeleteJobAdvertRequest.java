package com.kodlamaio.hrmns.hrmns.business.requests.delete;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteJobAdvertRequest {

	@NotNull
	@Positive
	private int id;
}
