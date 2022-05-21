package com.kodlamaio.hrmns.hrmns.business.abstracts;

import java.util.List;

import com.kodlamaio.hrmns.hrmns.business.dtos.GetListJobPositionDto;
import com.kodlamaio.hrmns.hrmns.business.requests.create.CreateJobPositionRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.delete.DeleteJobPositionRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.update.UpdateJobPositionRequest;
import com.kodlamaio.hrmns.hrmns.core.results.DataResult;
import com.kodlamaio.hrmns.hrmns.core.results.Result;

public interface JobPositionService {
	
	DataResult<GetListJobPositionDto> getByJobPositionId(short id);

	DataResult<List<GetListJobPositionDto>> getAll();

	DataResult<GetListJobPositionDto> getByJobPositionName(String name);

	Result add(CreateJobPositionRequest createJobPositionRequest);

	Result delete(DeleteJobPositionRequest deleteJobPositionRequest);

	Result update(UpdateJobPositionRequest updateJobPositionRequest);
	
	boolean checkIfJobPositionExists(short id);

}
