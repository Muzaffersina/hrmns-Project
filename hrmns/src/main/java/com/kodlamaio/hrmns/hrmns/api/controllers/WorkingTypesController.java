package com.kodlamaio.hrmns.hrmns.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.hrmns.hrmns.business.abstracts.WorkingTypeService;
import com.kodlamaio.hrmns.hrmns.business.dtos.GetListWorkingTypeDto;
import com.kodlamaio.hrmns.hrmns.business.requests.create.CreateWorkingTypeRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.delete.DeleteWorkingTypeRequest;
import com.kodlamaio.hrmns.hrmns.core.results.DataResult;
import com.kodlamaio.hrmns.hrmns.core.results.Result;

@RestController
@RequestMapping("/api/workingTypes")
public class WorkingTypesController {

	private WorkingTypeService workingTypeService;

	@Autowired
	public WorkingTypesController(WorkingTypeService workingTypeService) {
		this.workingTypeService = workingTypeService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateWorkingTypeRequest createWorkingTypeRequest) {
		return this.workingTypeService.add(createWorkingTypeRequest);
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestBody @Valid DeleteWorkingTypeRequest deleteWorkingTypeRequest) {
		return this.workingTypeService.delete(deleteWorkingTypeRequest);
	}

	@GetMapping("/getByWorkingTypeId")
	public DataResult<GetListWorkingTypeDto> getByWorkingTypeId(short id) {
		return this.workingTypeService.getByWorkingTypeId(id);
	}

	@GetMapping("/getAll")
	public DataResult<List<GetListWorkingTypeDto>> getAll() {
		return this.workingTypeService.getAll();
	}

	@GetMapping("/getByWorkingTypeName")
	public DataResult<GetListWorkingTypeDto> getByWorkingTypeName(String name){
		return this.workingTypeService.getByWorkingTypeName(name);
	}

}
