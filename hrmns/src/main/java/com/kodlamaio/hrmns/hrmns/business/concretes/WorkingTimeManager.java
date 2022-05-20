package com.kodlamaio.hrmns.hrmns.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.hrmns.hrmns.business.abstracts.WorkingTimeService;
import com.kodlamaio.hrmns.hrmns.business.dtos.GetListWorkingTimeDto;
import com.kodlamaio.hrmns.hrmns.business.dtos.GetListWorkingTypeDto;
import com.kodlamaio.hrmns.hrmns.business.requests.create.CreateWorkingTimeRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.delete.DeleteWorkingTimeRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.update.UpdateWorkingTimeRequest;
import com.kodlamaio.hrmns.hrmns.core.concretes.BusinessException;
import com.kodlamaio.hrmns.hrmns.core.mapping.ModelMapperService;
import com.kodlamaio.hrmns.hrmns.core.results.DataResult;
import com.kodlamaio.hrmns.hrmns.core.results.Result;
import com.kodlamaio.hrmns.hrmns.core.results.SuccessDataResult;
import com.kodlamaio.hrmns.hrmns.core.results.SuccessResult;
import com.kodlamaio.hrmns.hrmns.dataAccess.abstracts.WorkingTimeDao;
import com.kodlamaio.hrmns.hrmns.entities.WorkingTime;
import com.kodlamaio.hrmns.hrmns.entities.WorkingType;

@Service
public class WorkingTimeManager implements WorkingTimeService {

	private ModelMapperService modelMapperService;
	private WorkingTimeDao workingTimeDao;

	public WorkingTimeManager(ModelMapperService modelMapperService, WorkingTimeDao workingTimeDao) {
		this.modelMapperService = modelMapperService;
		this.workingTimeDao = workingTimeDao;
	}

	@Override
	public Result add(CreateWorkingTimeRequest createWorkingTimeRequest) {

		checkIfWorkingTimeNameExists(createWorkingTimeRequest.getName());

		WorkingTime workingTime = this.modelMapperService.forRequest().map(createWorkingTimeRequest, WorkingTime.class);
		this.workingTimeDao.save(workingTime);
		return new SuccessResult("Created Working Time");
	}

	@Override
	public Result delete(DeleteWorkingTimeRequest deleteWorkingTimeRequest) {

		checkIfWorkingTimeExists(deleteWorkingTimeRequest.getId());
		this.workingTimeDao.deleteById(deleteWorkingTimeRequest.getId());
		return new SuccessResult("Deleted Working Time");

	}

	@Override
	public Result update(UpdateWorkingTimeRequest updateWorkingTimeRequest) {

		checkIfWorkingTimeExists(updateWorkingTimeRequest.getId());
		return new SuccessResult("Updated Working Time");
	}

	@Override
	public DataResult<GetListWorkingTimeDto> getByWorkingTimeId(short id) {

		checkIfWorkingTimeExists(id);

		WorkingTime workingTime = this.workingTimeDao.getById(id);
		GetListWorkingTimeDto response = this.modelMapperService.forDto().map(workingTime, GetListWorkingTimeDto.class);

		return new SuccessDataResult<GetListWorkingTimeDto>(response);
	}

	@Override

	public DataResult<List<GetListWorkingTimeDto>> getAll() {

		List<WorkingTime> workingTimes = this.workingTimeDao.findAll();
		List<GetListWorkingTimeDto> response = workingTimes.stream()
				.map(workingTime -> this.modelMapperService.forDto().map(workingTime, GetListWorkingTimeDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetListWorkingTimeDto>>(response, "Listed Working Time");
	}

	@Override
	public DataResult<GetListWorkingTimeDto> getByWorkingTimeName(String name) {

		checkIfWorkingTimeExists(name);

		WorkingTime workingTime = this.workingTimeDao.getByName(name);
		GetListWorkingTimeDto response = this.modelMapperService.forDto().map(workingTime, GetListWorkingTimeDto.class);

		return new SuccessDataResult<GetListWorkingTimeDto>(response);
	}

	private boolean checkIfWorkingTimeExists(String name) {

		WorkingTime workingTime = this.workingTimeDao.getByName(name);
		if (workingTime != null) {
			return true;
		}
		throw new BusinessException("This working time doesnt found");
	}

	private boolean checkIfWorkingTimeNameExists(String name) {
		WorkingTime workingTime = this.workingTimeDao.getByName(name);
		if (workingTime == null) {
			return true;
		}
		throw new BusinessException("This working time already exists");
	}

	private boolean checkIfWorkingTimeExists(short id) {

		if (this.workingTimeDao.existsById(id)) {
			return true;
		}
		throw new BusinessException("This working time id doesnt found");
	}
}
