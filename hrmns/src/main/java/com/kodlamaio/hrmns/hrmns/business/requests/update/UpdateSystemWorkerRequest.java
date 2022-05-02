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
public class UpdateSystemWorkerRequest {
	
	@NotNull
	@Positive
	private int systemWorkerId;
	
	@NotNull
	@Size(min=5)
	private String email;
	
	@NotNull
	@Size(min=5)
	private String password;
	
	@NotNull
	@Size(min=10)
	private String phoneNumber;
	
	@NotNull
	@Size(min=1)
	private String firstName;
	
	@NotNull
	@Size(min=1)
	private String lastName;
	
	@NotNull
	@Size(min=1)
	private String position;


}
