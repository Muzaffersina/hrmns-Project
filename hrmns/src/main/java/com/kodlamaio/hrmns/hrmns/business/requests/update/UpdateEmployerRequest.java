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
public class UpdateEmployerRequest {
	
	@NotNull
	@Positive
	private int employerId;
	
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
	@Size(min=5)
	private String companyName;
	
	@NotNull
	@Size(min=5)
	private String companyWebsite;

}
