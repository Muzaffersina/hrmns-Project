package com.kodlamaio.hrmns.hrmns.business.abstracts;

import java.util.List;

import com.kodlamaio.hrmns.hrmns.business.dtos.GetListUserDto;
import com.kodlamaio.hrmns.hrmns.core.results.DataResult;

public interface UserService {
	
	DataResult<List<GetListUserDto>> getAll();
	DataResult<GetListUserDto> getByUserId(int userId);
	
	boolean checkIfEmailExists(String email);
	void validateEmail(int id , boolean status);

}
