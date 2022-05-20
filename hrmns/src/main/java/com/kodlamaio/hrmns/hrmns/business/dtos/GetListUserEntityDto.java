package com.kodlamaio.hrmns.hrmns.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetListUserEntityDto {
	

	private int id;

	private String email;

	private String password;
	
	private String phoneNumber;
	
	private boolean emailValidation;

}
