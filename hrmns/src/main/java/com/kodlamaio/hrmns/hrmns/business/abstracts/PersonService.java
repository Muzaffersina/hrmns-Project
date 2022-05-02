package com.kodlamaio.hrmns.hrmns.business.abstracts;

import java.util.List;

import com.kodlamaio.hrmns.hrmns.business.dtos.GetListPersonDto;
import com.kodlamaio.hrmns.hrmns.business.requests.create.CreatePersonRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.delete.DeletePersonRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.update.UpdatePersonRequest;
import com.kodlamaio.hrmns.hrmns.core.results.DataResult;
import com.kodlamaio.hrmns.hrmns.core.results.Result;

public interface PersonService {
	
	
	Result add(CreatePersonRequest createPersonRequest);
	Result delete(DeletePersonRequest deletePersonRequest);
	Result update(UpdatePersonRequest updatePersonRequest);
	
	DataResult<List<GetListPersonDto>> getAll();
	
	DataResult<GetListPersonDto> getByPersonId(int personId);
 	

}
