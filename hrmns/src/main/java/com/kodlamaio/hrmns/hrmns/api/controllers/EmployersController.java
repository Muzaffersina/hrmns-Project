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

import com.kodlamaio.hrmns.hrmns.business.abstracts.EmployerService;
import com.kodlamaio.hrmns.hrmns.business.dtos.GetListEmployerDto;
import com.kodlamaio.hrmns.hrmns.business.requests.create.CreateEmployerRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.delete.DeleteEmployerRequest;
import com.kodlamaio.hrmns.hrmns.core.results.DataResult;
import com.kodlamaio.hrmns.hrmns.core.results.Result;

@RestController
@RequestMapping("/api/employers")
public class EmployersController {

	private EmployerService employerService;

	@Autowired
	public EmployersController(EmployerService employerService) {
		this.employerService = employerService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateEmployerRequest createEmployerRequest) {
		return this.employerService.add(createEmployerRequest);
	}

	@DeleteMapping("/delete")
	public Result add(@RequestBody @Valid DeleteEmployerRequest deleteEmployerRequest) {
		return this.employerService.delete(deleteEmployerRequest);
	}

	@GetMapping("/getByEmployerId")
	public DataResult<GetListEmployerDto> getByEmployerId(int employerId){
		return this.employerService.getByEmployerId(employerId);
	}

	@GetMapping("/closeJobStatus")
	public Result closeJobStatus(int employerId, int jobId) {
		return this.employerService.closeJobStatus(employerId, jobId);
	}

	@GetMapping("/getAll")
	public DataResult<List<GetListEmployerDto>> getAll() {
		return this.employerService.getAll();
	}

}
