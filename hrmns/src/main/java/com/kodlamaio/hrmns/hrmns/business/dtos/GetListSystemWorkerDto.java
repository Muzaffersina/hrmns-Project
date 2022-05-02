package com.kodlamaio.hrmns.hrmns.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetListSystemWorkerDto {

	private int system_worker_id;
	
	private String email;

	private String password;

	private String phoneNumber;

	private String firstName;

	private String lastName;

	private String position;

}
