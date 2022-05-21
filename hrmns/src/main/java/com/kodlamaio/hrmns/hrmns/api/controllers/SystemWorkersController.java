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

import com.kodlamaio.hrmns.hrmns.business.abstracts.SystemWorkerService;
import com.kodlamaio.hrmns.hrmns.business.dtos.GetListSystemWorkerDto;
import com.kodlamaio.hrmns.hrmns.business.requests.create.CreateSystemWorkerRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.delete.DeleteSystemWorkerRequest;
import com.kodlamaio.hrmns.hrmns.core.results.DataResult;
import com.kodlamaio.hrmns.hrmns.core.results.Result;

@RestController
@RequestMapping("/api/systemWorkers")
public class SystemWorkersController {

	private SystemWorkerService systemWorkerService;

	@Autowired
	public SystemWorkersController(SystemWorkerService systemWorkerService) {
		this.systemWorkerService = systemWorkerService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateSystemWorkerRequest createSystemWorkerRequest) {
		return this.systemWorkerService.add(createSystemWorkerRequest);
	}

	@DeleteMapping("/delete")
	public Result add(@RequestBody @Valid DeleteSystemWorkerRequest deleteSystemWorkerRequest) {
		return this.systemWorkerService.delete(deleteSystemWorkerRequest);
	}

	@PostMapping("/jobStatusManuelChange")
	public Result jobStatusManuelChange(int jobId, boolean status) {
		return this.systemWorkerService.jobStatusManuelChange(jobId, status);
	}

	@PostMapping("/validateEmployer")
	public Result validateEmployerId(int id, boolean status) {
		return this.systemWorkerService.validateEmployerId(id, status);
	}
	
	@GetMapping("/getBySystemWorkerId")
	public DataResult<GetListSystemWorkerDto> getBySystemWorkerId(int systemWorkerId){
		return this.systemWorkerService.getBySystemWorkerId(systemWorkerId);
	}

	@GetMapping("/getAll")
	public DataResult<List<GetListSystemWorkerDto>> getAll() {
		return this.systemWorkerService.getAll();
	}

}
