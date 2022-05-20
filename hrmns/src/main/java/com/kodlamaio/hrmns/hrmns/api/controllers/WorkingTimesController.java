package com.kodlamaio.hrmns.hrmns.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.hrmns.hrmns.business.abstracts.WorkingTimeService;
import com.kodlamaio.hrmns.hrmns.business.dtos.GetListWorkingTimeDto;
import com.kodlamaio.hrmns.hrmns.business.requests.create.CreateWorkingTimeRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.delete.DeleteWorkingTimeRequest;
import com.kodlamaio.hrmns.hrmns.core.results.DataResult;
import com.kodlamaio.hrmns.hrmns.core.results.Result;

@RestController
@RequestMapping("/api/workingTimes")
public class WorkingTimesController {
	
	private WorkingTimeService workingTimeService;

	public WorkingTimesController(WorkingTimeService workingTimeService) {
		this.workingTimeService = workingTimeService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateWorkingTimeRequest createWorkingTimeRequest){
		return this.workingTimeService.add(createWorkingTimeRequest);
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestBody @Valid DeleteWorkingTimeRequest deleteWorkingTimeRequest) {
		return this.workingTimeService.delete(deleteWorkingTimeRequest);
	}
	
	@GetMapping("/getByWorkingTimeId")
	public DataResult<GetListWorkingTimeDto> getByWorkingTimeId(short id){
		return this.workingTimeService.getByWorkingTimeId(id);
	}

	@GetMapping("/getAll")
	public DataResult<List<GetListWorkingTimeDto>> getAll(){
		return this.workingTimeService.getAll();
	}

	@GetMapping("/getByWorkingTimeName")
	public DataResult<GetListWorkingTimeDto> getByWorkingTimeName(String name){
		return this.workingTimeService.getByWorkingTimeName(name);
	}


}
