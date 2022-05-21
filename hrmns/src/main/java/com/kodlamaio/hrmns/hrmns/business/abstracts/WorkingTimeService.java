package com.kodlamaio.hrmns.hrmns.business.abstracts;

import java.util.List;

import com.kodlamaio.hrmns.hrmns.business.dtos.GetListWorkingTimeDto;
import com.kodlamaio.hrmns.hrmns.business.requests.create.CreateWorkingTimeRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.delete.DeleteWorkingTimeRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.update.UpdateWorkingTimeRequest;
import com.kodlamaio.hrmns.hrmns.core.results.DataResult;
import com.kodlamaio.hrmns.hrmns.core.results.Result;

public interface WorkingTimeService {
	
	DataResult<GetListWorkingTimeDto> getByWorkingTimeId(short id);

	DataResult<List<GetListWorkingTimeDto>> getAll();

	DataResult<GetListWorkingTimeDto> getByWorkingTimeName(String name);

	Result add(CreateWorkingTimeRequest createWorkingTimeRequest);

	Result delete(DeleteWorkingTimeRequest deleteWorkingTimeRequest);

	Result update(UpdateWorkingTimeRequest updateWorkingTimeRequest);
	
	boolean checkIfWorkingTimeExists(short id);


}
