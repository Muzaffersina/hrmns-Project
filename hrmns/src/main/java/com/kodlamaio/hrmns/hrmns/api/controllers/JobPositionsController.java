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

import com.kodlamaio.hrmns.hrmns.business.abstracts.JobPositionService;
import com.kodlamaio.hrmns.hrmns.business.dtos.GetListJobPositionDto;
import com.kodlamaio.hrmns.hrmns.business.requests.create.CreateJobPositionRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.delete.DeleteJobPositionRequest;
import com.kodlamaio.hrmns.hrmns.core.results.DataResult;
import com.kodlamaio.hrmns.hrmns.core.results.Result;

@RestController
@RequestMapping("/api/JobPositions")
public class JobPositionsController {
	
	private JobPositionService jobPositionService;

	@Autowired
	public JobPositionsController(JobPositionService jobPositionService) {
		
		this.jobPositionService = jobPositionService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateJobPositionRequest createJobPositionRequest) {
		
		return this.jobPositionService.add(createJobPositionRequest);
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestBody @Valid DeleteJobPositionRequest deleteJobPositionRequest) {
		return this.jobPositionService.delete(deleteJobPositionRequest);
	}
	
	@GetMapping("/getByJobPositionId")
	public DataResult<GetListJobPositionDto> getByJobPositionId(short id){
		return this.jobPositionService.getByJobPositionId(id);
	}

	@GetMapping("/getAll")
	public DataResult<List<GetListJobPositionDto>> getAll(){
		return this.jobPositionService.getAll();
	}

	@GetMapping("/getByJobPositionName")
	public DataResult<GetListJobPositionDto> getByJobPositionName(String name){
		return this.jobPositionService.getByJobPositionName(name);
	}

}
