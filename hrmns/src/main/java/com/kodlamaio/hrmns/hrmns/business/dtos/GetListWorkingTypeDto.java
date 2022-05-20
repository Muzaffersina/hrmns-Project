package com.kodlamaio.hrmns.hrmns.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetListWorkingTypeDto {
	private short id;
	
	private String name;
}
