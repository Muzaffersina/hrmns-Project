package com.kodlamaio.hrmns.hrmns.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrmns.hrmns.business.abstracts.CityService;
import com.kodlamaio.hrmns.hrmns.business.dtos.GetListCityDto;
import com.kodlamaio.hrmns.hrmns.core.concretes.BusinessException;
import com.kodlamaio.hrmns.hrmns.core.mapping.ModelMapperService;
import com.kodlamaio.hrmns.hrmns.core.results.DataResult;
import com.kodlamaio.hrmns.hrmns.core.results.SuccessDataResult;
import com.kodlamaio.hrmns.hrmns.dataAccess.abstracts.CityDao;
import com.kodlamaio.hrmns.hrmns.entities.City;
@Service
public class CityManager implements CityService {

	private ModelMapperService modelMapperService;
	private CityDao cityDao;

	@Autowired
	public CityManager(ModelMapperService modelMapperService, CityDao cityDao) {

		this.modelMapperService = modelMapperService;
		this.cityDao = cityDao;
	}

	@Override
	public DataResult<GetListCityDto> getByNameCity(String name) {
		
		City city= isCityNameExists(name);
		GetListCityDto response = this.modelMapperService.forDto().map(city, GetListCityDto.class);
		
		return new SuccessDataResult<GetListCityDto>(response);
	}
	
	private City isCityNameExists(String name) {
		
		City city = this.cityDao.getByCityName(name);
		if(city != null) {
			return city; 
		}
		throw new BusinessException("This city name doesnt found");
	}

}
