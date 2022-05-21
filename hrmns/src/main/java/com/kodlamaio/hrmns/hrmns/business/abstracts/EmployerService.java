package com.kodlamaio.hrmns.hrmns.business.abstracts;

import java.util.List;

import com.kodlamaio.hrmns.hrmns.business.dtos.GetListEmployerDto;
import com.kodlamaio.hrmns.hrmns.business.requests.create.CreateEmployerRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.delete.DeleteEmployerRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.update.UpdateEmployerRequest;
import com.kodlamaio.hrmns.hrmns.core.results.DataResult;
import com.kodlamaio.hrmns.hrmns.core.results.Result;

public interface EmployerService {
	
	Result add(CreateEmployerRequest createEmployerRequest);
	Result delete(DeleteEmployerRequest deleteEmployerRequest);
	Result update(UpdateEmployerRequest updateEmployerRequest);
	
	DataResult<List<GetListEmployerDto>> getAll();
	DataResult<GetListEmployerDto> getByEmployerId(int employerId);
	
	boolean checkIfEmployerExists(int employerId);
	void validateEmployerId(int id, boolean status);
	Result closeJobStatus(int employerId,int jobId);
	boolean checkIfValidatedEmployer(int employerId);
}
