package com.kodlamaio.hrmns.hrmns.api.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.hrmns.hrmns.business.abstracts.JobService;
import com.kodlamaio.hrmns.hrmns.business.dtos.GetListJobDto;
import com.kodlamaio.hrmns.hrmns.business.requests.create.CreateJobRequest;
import com.kodlamaio.hrmns.hrmns.core.results.DataResult;
import com.kodlamaio.hrmns.hrmns.core.results.Result;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

	private JobService jobService;

	@Autowired
	public JobController(JobService jobService) {
		this.jobService = jobService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateJobRequest createJobRequest) {
		return this.jobService.add(createJobRequest);
	}

	@GetMapping("/getAll")
	public DataResult<List<GetListJobDto>> getAll() {
		return this.jobService.getAll();
	}

	@GetMapping("/getAllByJobStatusTrue")
	public DataResult<List<GetListJobDto>> getAllByJobStatusTrue() {
		return this.jobService.getAllByJobStatusTrue();
	}

	@GetMapping("/getJobStatusTrueByEmployer")
	public DataResult<List<GetListJobDto>> getJobStatusTrueByEmployer(int employerId) {
		return this.jobService.getJobStatusTrueByEmployer(employerId);
	}

	@GetMapping("/getByEmployer")
	public DataResult<List<GetListJobDto>> getByEmployerId(int employerId) {
		return this.jobService.getByEmployerId(employerId);
	}

	@GetMapping("/getAllJobStatusTrueByDate/{start_date}/{end_date}")
	public DataResult<List<GetListJobDto>> getAllJobStatusTrueByDate(
			@PathVariable(value = "start_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
			@PathVariable(value = "end_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
		
				return this.jobService.getAllJobStatusTrueByDate(startDate, endDate);
	}

}
