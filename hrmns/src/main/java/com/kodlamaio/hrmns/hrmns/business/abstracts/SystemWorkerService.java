package com.kodlamaio.hrmns.hrmns.business.abstracts;

import java.util.List;

import com.kodlamaio.hrmns.hrmns.business.dtos.GetListSystemWorkerDto;
import com.kodlamaio.hrmns.hrmns.business.requests.create.CreateSystemWorkerRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.delete.DeleteSystemWorkerRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.update.UpdateSystemWorkerRequest;
import com.kodlamaio.hrmns.hrmns.core.results.DataResult;
import com.kodlamaio.hrmns.hrmns.core.results.Result;

public interface SystemWorkerService {
	
	Result add(CreateSystemWorkerRequest createSystemWorkerRequest);
	Result delete(DeleteSystemWorkerRequest deleteSystemWorkerRequest);
	Result update(UpdateSystemWorkerRequest updateSystemWorkerRequest);
	
	DataResult<List<GetListSystemWorkerDto>> getAll();
	
	DataResult<GetListSystemWorkerDto> getBySystemWorkerId(int systemWorkerId);
	
	Result validateEmployerId(int id, boolean status);
	
	Result jobStatusManuelChange(int jobId,boolean status);

}
