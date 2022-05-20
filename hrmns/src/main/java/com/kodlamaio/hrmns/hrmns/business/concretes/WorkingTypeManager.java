package com.kodlamaio.hrmns.hrmns.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrmns.hrmns.business.abstracts.WorkingTypeService;
import com.kodlamaio.hrmns.hrmns.business.dtos.GetListWorkingTypeDto;
import com.kodlamaio.hrmns.hrmns.business.requests.create.CreateWorkingTypeRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.delete.DeleteWorkingTypeRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.update.UpdateWorkingTypeRequest;
import com.kodlamaio.hrmns.hrmns.core.concretes.BusinessException;
import com.kodlamaio.hrmns.hrmns.core.mapping.ModelMapperService;
import com.kodlamaio.hrmns.hrmns.core.results.DataResult;
import com.kodlamaio.hrmns.hrmns.core.results.Result;
import com.kodlamaio.hrmns.hrmns.core.results.SuccessDataResult;
import com.kodlamaio.hrmns.hrmns.core.results.SuccessResult;
import com.kodlamaio.hrmns.hrmns.dataAccess.abstracts.WorkingTypeDao;
import com.kodlamaio.hrmns.hrmns.entities.WorkingType;

@Service
public class WorkingTypeManager implements WorkingTypeService {

	private ModelMapperService modelMapperService;
	private WorkingTypeDao workingTypeDao;

	@Autowired
	public WorkingTypeManager(ModelMapperService modelMapperService, WorkingTypeDao workingTypeDao) {
		this.modelMapperService = modelMapperService;
		this.workingTypeDao = workingTypeDao;
	}

	@Override
	public Result add(CreateWorkingTypeRequest createWorkingTypeRequest) {

		checkIfWorkingTypeNameExists(createWorkingTypeRequest.getName());
		
		WorkingType workingType = this.modelMapperService.forRequest().map(createWorkingTypeRequest, WorkingType.class);
		this.workingTypeDao.save(workingType);
		
		return new SuccessResult("Created Working Type");
	}

	@Override
	public Result delete(DeleteWorkingTypeRequest deleteWorkingTypeRequest) {

		checkIfWorkingTypeExists(deleteWorkingTypeRequest.getId());	
		this.workingTypeDao.deleteById(deleteWorkingTypeRequest.getId());
		
		return new SuccessResult("Deleted Working Type");
	}

	@Override
	public Result update(UpdateWorkingTypeRequest updateWorkingTypeRequest) {

		checkIfWorkingTypeExists(updateWorkingTypeRequest.getId());
		return new SuccessResult("Updated Working Type");
	}

	@Override
	public DataResult<List<GetListWorkingTypeDto>> getAll() {

		List<WorkingType> workingTypes = this.workingTypeDao.findAll();
		List<GetListWorkingTypeDto> response = workingTypes.stream()
				.map(workingType -> this.modelMapperService.forDto().map(workingType, GetListWorkingTypeDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetListWorkingTypeDto>>(response, "Listed Working Types");
	}

	@Override
	public DataResult<GetListWorkingTypeDto> getByWorkingTypeName(String name) {

		checkIfWorkingTypeExists(name);

		WorkingType workingType = this.workingTypeDao.getByName(name);
		GetListWorkingTypeDto response = this.modelMapperService.forDto().map(workingType, GetListWorkingTypeDto.class);

		return new SuccessDataResult<GetListWorkingTypeDto>(response);
	}
	
	@Override
	public DataResult<GetListWorkingTypeDto> getByWorkingTypeId(short id) {
		
		checkIfWorkingTypeExists(id);
		
		WorkingType workingType = this.workingTypeDao.getById(id);
		GetListWorkingTypeDto response = this.modelMapperService.forDto().map(workingType, GetListWorkingTypeDto.class);
		
		return new SuccessDataResult<GetListWorkingTypeDto>(response);
	}

	private boolean checkIfWorkingTypeExists(String name) {

		WorkingType workingType = this.workingTypeDao.getByName(name);
		if (workingType != null) {
			return true;
		}
		throw new BusinessException("This working type doesnt found");
	}

	private boolean checkIfWorkingTypeNameExists(String name) {
		
		WorkingType workingType = this.workingTypeDao.getByName(name);
		if (workingType == null) {
			return true;
		}
		throw new BusinessException("This working type already exists");
	}

	private boolean checkIfWorkingTypeExists(short id) {

		if (this.workingTypeDao.existsById(id)) {
			return true;
		}
		throw new BusinessException("This working type id doesnt found");
	}


}
