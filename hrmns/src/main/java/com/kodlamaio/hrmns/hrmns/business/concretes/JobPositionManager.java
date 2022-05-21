package com.kodlamaio.hrmns.hrmns.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrmns.hrmns.business.abstracts.JobPositionService;
import com.kodlamaio.hrmns.hrmns.business.dtos.GetListJobPositionDto;
import com.kodlamaio.hrmns.hrmns.business.requests.create.CreateJobPositionRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.delete.DeleteJobPositionRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.update.UpdateJobPositionRequest;
import com.kodlamaio.hrmns.hrmns.core.concretes.BusinessException;
import com.kodlamaio.hrmns.hrmns.core.mapping.ModelMapperService;
import com.kodlamaio.hrmns.hrmns.core.results.DataResult;
import com.kodlamaio.hrmns.hrmns.core.results.Result;
import com.kodlamaio.hrmns.hrmns.core.results.SuccessDataResult;
import com.kodlamaio.hrmns.hrmns.core.results.SuccessResult;
import com.kodlamaio.hrmns.hrmns.dataAccess.abstracts.JobPositionDao;
import com.kodlamaio.hrmns.hrmns.entities.JobPosition;

@Service
public class JobPositionManager implements JobPositionService {

	private ModelMapperService modelMapperService;
	private JobPositionDao jobPositionDao;

	@Autowired
	public JobPositionManager(ModelMapperService modelMapperService, JobPositionDao jobPositionDao) {

		this.modelMapperService = modelMapperService;
		this.jobPositionDao = jobPositionDao;
	}

	@Override
	public Result add(CreateJobPositionRequest createJobPositionRequest) {
		
		createJobPositionRequest.setName(toUpperCase(createJobPositionRequest.getName()));
		checkIfJobPositionNameExists(createJobPositionRequest.getName());
	

		JobPosition jobPosition = this.modelMapperService.forRequest().map(createJobPositionRequest, JobPosition.class);
		this.jobPositionDao.save(jobPosition);

		return new SuccessResult("Created Job Position");

	}

	@Override
	public Result delete(DeleteJobPositionRequest deleteJobPositionRequest) {

		checkIfJobPositionExists(deleteJobPositionRequest.getId());
		this.jobPositionDao.deleteById(deleteJobPositionRequest.getId());

		return new SuccessResult("Deleted Job Position");
	}

	@Override
	public Result update(UpdateJobPositionRequest updateJobPositionRequest) {

		updateJobPositionRequest.setName(toUpperCase(updateJobPositionRequest.getName()));
		checkIfJobPositionExists(updateJobPositionRequest.getId());
		return new SuccessResult("Updated Job Position");
	}

	@Override
	public DataResult<GetListJobPositionDto> getByJobPositionId(short id) {

		checkIfJobPositionExists(id);

		JobPosition jobPosition = this.jobPositionDao.getById(id);
		GetListJobPositionDto response = this.modelMapperService.forDto().map(jobPosition, GetListJobPositionDto.class);

		return new SuccessDataResult<GetListJobPositionDto>(response);
	}

	@Override
	public DataResult<List<GetListJobPositionDto>> getAll() {

		List<JobPosition> jobPositions = this.jobPositionDao.findAll();
		List<GetListJobPositionDto> response = jobPositions.stream()
				.map(jobPosiiton -> this.modelMapperService.forDto().map(jobPosiiton, GetListJobPositionDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetListJobPositionDto>>(response, "Listed Job Positions");
	}

	@Override
	public DataResult<GetListJobPositionDto> getByJobPositionName(String name) {
		
		name = toUpperCase(name);
		checkIfJobPositionExists(name);

		JobPosition jobPosition = this.jobPositionDao.getByName(name);
		GetListJobPositionDto response = this.modelMapperService.forDto().map(jobPosition, GetListJobPositionDto.class);

		return new SuccessDataResult<GetListJobPositionDto>(response);
	}

	private boolean checkIfJobPositionExists(String name) {

		JobPosition jobPosition = this.jobPositionDao.getByName(name);
		if (jobPosition != null) {
			return true;
		}
		throw new BusinessException("This job position doesnt found");
	}

	private boolean checkIfJobPositionNameExists(String name) {

		JobPosition jobPosition = this.jobPositionDao.getByName(name);
		if (jobPosition == null) {
			return true;
		}
		throw new BusinessException("This job position doesnt found");
	}

	@Override
	public boolean checkIfJobPositionExists(short id) {

		if (this.jobPositionDao.existsById(id)) {
			return true;
		}
		throw new BusinessException("This job position id doesnt found");
	}
	
	private String toUpperCase(String name) {
		return name.toUpperCase();
	}

}
