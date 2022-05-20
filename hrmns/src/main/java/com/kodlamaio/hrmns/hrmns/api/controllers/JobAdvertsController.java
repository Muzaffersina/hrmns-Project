package com.kodlamaio.hrmns.hrmns.api.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.hrmns.hrmns.business.abstracts.JobAdvertService;
import com.kodlamaio.hrmns.hrmns.business.dtos.GetListJobAdvertDto;
import com.kodlamaio.hrmns.hrmns.business.requests.create.CreateJobAdvertRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.delete.DeleteJobAdvertRequest;
import com.kodlamaio.hrmns.hrmns.core.results.DataResult;
import com.kodlamaio.hrmns.hrmns.core.results.Result;

@RestController
@RequestMapping("/api/jobAdverts")
public class JobAdvertsController {

	private JobAdvertService jobService;

	@Autowired
	public JobAdvertsController(JobAdvertService jobService) {
		this.jobService = jobService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateJobAdvertRequest createJobRequest) {
		return this.jobService.add(createJobRequest);
	}
	
	@DeleteMapping("/delete")
	public Result delete(@RequestBody @Valid DeleteJobAdvertRequest deleteJobAdvertRequest) {
		return this.jobService.delete(deleteJobAdvertRequest);
	}

	@GetMapping("/getAll")
	public DataResult<List<GetListJobAdvertDto>> getAll() {
		return this.jobService.getAll();
	}

	@GetMapping("/getAllByJobStatusTrue")
	public DataResult<List<GetListJobAdvertDto>> getAllByJobStatusTrue() {
		return this.jobService.getAllByJobStatusTrue();
	}

	@GetMapping("/getJobStatusTrueByEmployer")
	public DataResult<List<GetListJobAdvertDto>> getJobStatusTrueByEmployer(int employerId) {
		return this.jobService.getJobStatusTrueByEmployer(employerId);
	}

	@GetMapping("/getByEmployer")
	public DataResult<List<GetListJobAdvertDto>> getByEmployerId(int employerId) {
		return this.jobService.getByEmployerId(employerId);
	}

	@GetMapping("/getAllJobStatusTrueByDate/{start_date}/{end_date}")
	public DataResult<List<GetListJobAdvertDto>> getAllJobStatusTrueByDate(
			@PathVariable(value = "start_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
			@PathVariable(value = "end_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
		
				return this.jobService.getAllJobStatusTrueByDate(startDate, endDate);
	}

}
