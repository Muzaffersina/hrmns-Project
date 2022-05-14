package com.kodlamaio.hrmns.hrmns.business.abstracts;

import com.kodlamaio.hrmns.hrmns.business.dtos.GetListCityDto;
import com.kodlamaio.hrmns.hrmns.core.results.DataResult;

public interface CityService {
	
	DataResult<GetListCityDto> getByNameCity(String name);

}
