package com.kodlamaio.hrmns.hrmns.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetListEmployerDto {
	
	private int employerId;
	
	private boolean systemWorkerValidate;
	
	private String email;
	
	private String password;
	
	private String phoneNumber;
	
	private String companyName;
	
	private String companyWebsite;

}
