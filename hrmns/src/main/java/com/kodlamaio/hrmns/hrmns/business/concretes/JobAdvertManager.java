package com.kodlamaio.hrmns.hrmns.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrmns.hrmns.business.abstracts.CityService;
import com.kodlamaio.hrmns.hrmns.business.abstracts.EmployerService;
import com.kodlamaio.hrmns.hrmns.business.abstracts.JobAdvertService;
import com.kodlamaio.hrmns.hrmns.business.abstracts.JobPositionService;
import com.kodlamaio.hrmns.hrmns.business.abstracts.WorkingTimeService;
import com.kodlamaio.hrmns.hrmns.business.abstracts.WorkingTypeService;
import com.kodlamaio.hrmns.hrmns.business.dtos.GetListJobAdvertDto;
import com.kodlamaio.hrmns.hrmns.business.requests.create.CreateJobAdvertRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.delete.DeleteJobAdvertRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.update.UpdateJobAdvertRequest;
import com.kodlamaio.hrmns.hrmns.core.concretes.BusinessException;
import com.kodlamaio.hrmns.hrmns.core.mapping.ModelMapperService;
import com.kodlamaio.hrmns.hrmns.core.results.DataResult;
import com.kodlamaio.hrmns.hrmns.core.results.Result;
import com.kodlamaio.hrmns.hrmns.core.results.SuccessDataResult;
import com.kodlamaio.hrmns.hrmns.core.results.SuccessResult;
import com.kodlamaio.hrmns.hrmns.dataAccess.abstracts.JobAdvertDao;
import com.kodlamaio.hrmns.hrmns.entities.JobAdvert;

@Service
public class JobAdvertManager implements JobAdvertService {

	private JobAdvertDao jobAdvertDao;
	private ModelMapperService modelMapperService;
	private EmployerService employerService;
	private JobPositionService jobPositionService;
	private CityService cityService;
	private WorkingTimeService workingTimeService;
	private WorkingTypeService workingTypeService;
	

	@Autowired
	@Lazy
	public JobAdvertManager(JobAdvertDao jobAdvertDao, ModelMapperService modelMapperService, EmployerService employerService,
			JobPositionService jobPositionService, CityService cityService, WorkingTimeService workingTimeService,
			WorkingTypeService workingTypeService) {
		this.jobAdvertDao = jobAdvertDao;
		this.modelMapperService = modelMapperService;
		this.employerService = employerService;
		this.jobPositionService = jobPositionService;
		this.cityService = cityService;
		this.workingTimeService = workingTimeService;
		this.workingTypeService = workingTypeService;

	}

	@Override
	public Result add(CreateJobAdvertRequest createJobRequest) {

		this.employerService.checkIfEmployerExists(createJobRequest.getEmployerEmployerId());
		this.employerService.checkIfValidatedEmployer(createJobRequest.getEmployerEmployerId());
		this.jobPositionService.checkIfJobPositionExists(createJobRequest.getJobPositionId());
		this.cityService.checkIfCityIdExists(createJobRequest.getCityId());
		this.workingTimeService.checkIfWorkingTimeExists(createJobRequest.getWorkingTimeId());
		this.workingTypeService.checkIfWorkingTypeExists(createJobRequest.getWorkingTypeId());
		
		
		JobAdvert job = this.modelMapperService.forRequest().map(createJobRequest, JobAdvert.class);
		job.setCreateDate(LocalDate.now());
		job.setStatus(true);
		job.setId(0);
		
		this.jobAdvertDao.save(job);

		return new SuccessResult("Created Job");

	}

	@Override
	public Result delete(DeleteJobAdvertRequest deleteJobRequest) {

		checkIfJobExists(deleteJobRequest.getId());
		this.jobAdvertDao.deleteById(deleteJobRequest.getId());
		return new SuccessResult("Deleted Job");
	}

	@Override
	public Result update(UpdateJobAdvertRequest updateJobRequest) {
		checkIfJobExists(updateJobRequest.getId());
		return null;
	}

	@Override
	public DataResult<List<GetListJobAdvertDto>> getAll() {

		List<JobAdvert> jobs = this.jobAdvertDao.findAll();
		List<GetListJobAdvertDto> response = jobs.stream()
				.map(job -> this.modelMapperService.forDto().map(job, GetListJobAdvertDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetListJobAdvertDto>>(response, "Listed Jobs");
	}

	@Override
	public DataResult<List<GetListJobAdvertDto>> getByEmployerId(int employerId) {

		this.employerService.checkIfEmployerExists(employerId);
		List<JobAdvert> jobs = this.jobAdvertDao.getByEmployer_EmployerId(employerId);
		List<GetListJobAdvertDto> response = jobs.stream()
				.map(job -> this.modelMapperService.forDto().map(job, GetListJobAdvertDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetListJobAdvertDto>>(response, "Listed Jobs For Employer");
	}

	@Override
	public DataResult<List<GetListJobAdvertDto>> getAllByJobStatusTrue() {

		List<JobAdvert> jobs = this.jobAdvertDao.getByStatus(true);
		List<GetListJobAdvertDto> response = jobs.stream()
				.map(job -> this.modelMapperService.forDto().map(job, GetListJobAdvertDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetListJobAdvertDto>>(response, "Listed Jobs By Status=True");
	}

	@Override
	public DataResult<List<GetListJobAdvertDto>> getJobStatusTrueByEmployer(int employerId) {

		List<JobAdvert> jobs = this.jobAdvertDao.getByEmployer_EmployerIdAndStatus(employerId, true);
		List<GetListJobAdvertDto> response = jobs.stream()
				.map(job -> this.modelMapperService.forDto().map(job, GetListJobAdvertDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetListJobAdvertDto>>(response, "Listed Jobs Status=True By Employer");
	}

	@Override
	public DataResult<List<GetListJobAdvertDto>> getAllJobStatusTrueByDate(LocalDate startDate, LocalDate endDate) {

		List<JobAdvert> jobs = this.jobAdvertDao.getByDeadLineBetween(startDate, endDate);
		List<GetListJobAdvertDto> response = jobs.stream()
				.map(job -> this.modelMapperService.forDto().map(job, GetListJobAdvertDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetListJobAdvertDto>>(response, "Listed Jobs By DeadLine");
	}

	@Override
	public void jobStatusManuelChange(int jobId, boolean status) {

		checkIfJobExists(jobId);
		JobAdvert job = this.jobAdvertDao.getById(jobId);
		job.setStatus(status);
		this.jobAdvertDao.save(job);

	}

	@Override
	public Result closeJobStatusByEmployer(int employerId, int jobId, boolean status) {

		this.employerService.checkIfEmployerExists(employerId);
		checkIfJobExists(jobId);

		List<JobAdvert> jobs = this.jobAdvertDao.getByEmployer_EmployerId(employerId);
		for (JobAdvert job : jobs) {
			if (job.getEmployer().getEmployerId() == employerId) {

				if (job.isStatus() == true) {
					job.setStatus(false);
					this.jobAdvertDao.save(job);
				}
			}
		}
		throw new BusinessException("This job status is already closed");

	}

	private boolean checkIfJobExists(int id) {

		if (this.jobAdvertDao.existsById(id)) {
			return true;
		}
		throw new BusinessException("This job id not found");
	}

}
