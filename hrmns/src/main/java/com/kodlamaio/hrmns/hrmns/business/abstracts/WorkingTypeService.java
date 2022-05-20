package com.kodlamaio.hrmns.hrmns.business.abstracts;

import java.util.List;

import com.kodlamaio.hrmns.hrmns.business.dtos.GetListWorkingTypeDto;
import com.kodlamaio.hrmns.hrmns.business.requests.create.CreateWorkingTypeRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.delete.DeleteWorkingTypeRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.update.UpdateWorkingTypeRequest;
import com.kodlamaio.hrmns.hrmns.core.results.DataResult;
import com.kodlamaio.hrmns.hrmns.core.results.Result;

public interface WorkingTypeService {
	
	DataResult<GetListWorkingTypeDto> getByWorkingTypeId(short id);

	DataResult<List<GetListWorkingTypeDto>> getAll();

	DataResult<GetListWorkingTypeDto> getByWorkingTypeName(String name);

	Result add(CreateWorkingTypeRequest createWorkingTypeRequest);

	Result delete(DeleteWorkingTypeRequest deleteWorkingTypeRequest);

	Result update(UpdateWorkingTypeRequest updateWorkingTypeRequest);

	

}
