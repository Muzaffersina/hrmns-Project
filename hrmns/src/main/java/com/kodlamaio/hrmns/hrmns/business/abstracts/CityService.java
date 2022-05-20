package com.kodlamaio.hrmns.hrmns.business.abstracts;

import java.util.List;

import com.kodlamaio.hrmns.hrmns.business.dtos.GetListCityDto;
import com.kodlamaio.hrmns.hrmns.business.requests.create.CreateCityRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.delete.DeleteCityRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.update.UpdateCityRequest;
import com.kodlamaio.hrmns.hrmns.core.results.DataResult;
import com.kodlamaio.hrmns.hrmns.core.results.Result;

public interface CityService {
	Result add(CreateCityRequest createCityRequest);
	Result delete(DeleteCityRequest deleteCityRequest);
	Result update(UpdateCityRequest updateCityRequest);
	
	DataResult<List<GetListCityDto>> getAll();
	DataResult<GetListCityDto> getByNameCity(String name);
	DataResult<GetListCityDto> getById(short id);

}
