package com.kodlamaio.hrmns.hrmns.business.requests.create;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePersonRequest {
	
	@NotNull
	@Size(min=5)
	private String email;
	
	@NotNull
	@Size(min=5)
	private String password;
	
	@NotNull
	@Size(min=10,max = 11)
	private String phoneNumber;
	
	@NotNull
	@Size(min=1)
	private String firstName;
	
	@NotNull
	@Size(min=1)
	private String lastName;

	@NotNull
	@Positive
	private int birthDate;
	
	@NotNull
	@Size(min=11,max = 12)
	private String socialSecurityNumber;

}
