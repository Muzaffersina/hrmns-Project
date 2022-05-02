package com.kodlamaio.hrmns.hrmns.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrmns.hrmns.business.abstracts.EmployerService;
import com.kodlamaio.hrmns.hrmns.business.abstracts.JobService;
import com.kodlamaio.hrmns.hrmns.business.dtos.GetListJobDto;
import com.kodlamaio.hrmns.hrmns.business.requests.create.CreateJobRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.delete.DeleteJobRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.update.UpdateJobRequest;
import com.kodlamaio.hrmns.hrmns.core.concretes.BusinessException;
import com.kodlamaio.hrmns.hrmns.core.mapping.ModelMapperService;
import com.kodlamaio.hrmns.hrmns.core.results.DataResult;
import com.kodlamaio.hrmns.hrmns.core.results.Result;
import com.kodlamaio.hrmns.hrmns.core.results.SuccessDataResult;
import com.kodlamaio.hrmns.hrmns.core.results.SuccessResult;
import com.kodlamaio.hrmns.hrmns.dataAccess.abstracts.JobDao;
import com.kodlamaio.hrmns.hrmns.entities.Job;

@Service
public class JobManager implements JobService {

	private JobDao jobDao;
	private ModelMapperService modelMapperService;
	private EmployerService employerService;

	@Autowired
	@Lazy
	public JobManager(JobDao jobDao, ModelMapperService modelMapperService, EmployerService employerService) {
		this.jobDao = jobDao;
		this.modelMapperService = modelMapperService;
		this.employerService = employerService;
	}

	@Override
	public Result add(CreateJobRequest createJobRequest) {

		this.employerService.checkIfEmployerExists(createJobRequest.getEmployerEmployerId());
		Job job = this.modelMapperService.forRequest().map(createJobRequest, Job.class);
		job.setCreateDate(LocalDate.now());
		job.setStatus(true);
		job.setId(0);
		this.jobDao.save(job);

		return new SuccessResult("Created Job");

	}

	@Override
	public Result delete(DeleteJobRequest deleteJobRequest) {

		checkIfJobExists(deleteJobRequest.getId());
		this.jobDao.deleteById(deleteJobRequest.getId());
		return new SuccessResult("Deleted Job");
	}

	@Override
	public Result update(UpdateJobRequest updateJobRequest) {
		checkIfJobExists(updateJobRequest.getId());
		return null;
	}

	@Override
	public DataResult<List<GetListJobDto>> getAll() {

		List<Job> jobs = this.jobDao.findAll();
		List<GetListJobDto> response = jobs.stream()
				.map(job -> this.modelMapperService.forDto().map(job, GetListJobDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetListJobDto>>(response, "Listed Jobs");
	}

	@Override
	public DataResult<List<GetListJobDto>> getByEmployerId(int employerId) {

		this.employerService.checkIfEmployerExists(employerId);
		List<Job> jobs = this.jobDao.getByEmployer_EmployerId(employerId);
		List<GetListJobDto> response = jobs.stream()
				.map(job -> this.modelMapperService.forDto().map(job, GetListJobDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetListJobDto>>(response, "Listed Jobs For Employer");
	}
	
	@Override
	public DataResult<List<GetListJobDto>> getAllByJobStatusTrue() {
		
		List<Job> jobs = this.jobDao.getByStatus(true);
		List<GetListJobDto> response = jobs.stream()
				.map(job -> this.modelMapperService.forDto().map(job, GetListJobDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetListJobDto>>(response, "Listed Jobs By Status=True");	
	}

	@Override
	public DataResult<List<GetListJobDto>> getJobStatusTrueByEmployer(int employerId) {
		
		List<Job> jobs = this.jobDao.getByEmployer_EmployerIdAndStatus(employerId, true);
		List<GetListJobDto> response = jobs.stream()
				.map(job -> this.modelMapperService.forDto().map(job, GetListJobDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetListJobDto>>(response, "Listed Jobs Status=True By Employer");	
	}
	

	@Override
	public DataResult<List<GetListJobDto>> getAllJobStatusTrueByDate(LocalDate startDate, LocalDate endDate) {
		
		List<Job> jobs = this.jobDao.getByDeadLineBetween(startDate, endDate);
		List<GetListJobDto> response = jobs.stream()
				.map(job -> this.modelMapperService.forDto().map(job, GetListJobDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetListJobDto>>(response, "Listed Jobs By DeadLine");	
	}


	@Override
	public void jobStatusManuelChange(int jobId, boolean status) {

		checkIfJobExists(jobId);
		Job job = this.jobDao.getById(jobId);
		job.setStatus(status);
		this.jobDao.save(job);

	}

	@Override
	public Result closeJobStatusByEmployer(int employerId, int jobId, boolean status) {

		this.employerService.checkIfEmployerExists(employerId);
		checkIfJobExists(jobId);

		List<Job> jobs = this.jobDao.getByEmployer_EmployerId(employerId);
		for (Job job : jobs) {
			if (job.getEmployer().getEmployerId() == employerId) {

				if (job.isStatus() == true) {
					job.setStatus(false);
					this.jobDao.save(job);
				}
			}
		}
		throw new BusinessException("This job status is already closed");

	}

	private boolean checkIfJobExists(int id) {

		if (this.jobDao.existsById(id)) {
			return true;
		}
		throw new BusinessException("This job id not found");
	}



}
