package com.kodlamaio.hrmns.hrmns.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetListPersonDto {

	private int personId;
	
	private String email;
	
	private String password;
	
	private String phoneNumber;
	
	private String firstName;

	private String lastName;

	private int birthDate;

	private String socialSecurityNumber;

}
