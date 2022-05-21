package com.kodlamaio.hrmns.hrmns.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.hrmns.hrmns.business.abstracts.UserEntityService;
import com.kodlamaio.hrmns.hrmns.business.dtos.GetListUserEntityDto;
import com.kodlamaio.hrmns.hrmns.core.results.DataResult;

@RestController
@RequestMapping("/api/userControllers")
public class UsersController {
	
	private UserEntityService userService;
	
	@Autowired 
	public UsersController(UserEntityService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<GetListUserEntityDto>> getAll(){
		return this.userService.getAll();
	}
	
	@GetMapping("/getByUserId")
	public DataResult<GetListUserEntityDto> getByUserId(int userId){
		return this.userService.getByUserId(userId);
	}
}
