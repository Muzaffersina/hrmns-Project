package com.kodlamaio.hrmns.hrmns.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrmns.hrmns.business.abstracts.CityService;
import com.kodlamaio.hrmns.hrmns.business.dtos.GetListCityDto;
import com.kodlamaio.hrmns.hrmns.business.requests.create.CreateCityRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.delete.DeleteCityRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.update.UpdateCityRequest;
import com.kodlamaio.hrmns.hrmns.core.concretes.BusinessException;
import com.kodlamaio.hrmns.hrmns.core.mapping.ModelMapperService;
import com.kodlamaio.hrmns.hrmns.core.results.DataResult;
import com.kodlamaio.hrmns.hrmns.core.results.Result;
import com.kodlamaio.hrmns.hrmns.core.results.SuccessDataResult;
import com.kodlamaio.hrmns.hrmns.core.results.SuccessResult;
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
	public Result add(CreateCityRequest createCityRequest) {

		isCityNameExists(createCityRequest.getName());

		City city = this.modelMapperService.forRequest().map(createCityRequest, City.class);

		this.cityDao.save(city);

		return new SuccessResult("Created City");
	}

	@Override
	public Result delete(DeleteCityRequest deleteCityRequest) {

		checkIfCityIdExists(deleteCityRequest.getId());
		this.cityDao.deleteById(deleteCityRequest.getId());

		return new SuccessResult("Deleted City");
	}

	@Override
	public Result update(UpdateCityRequest updateCityRequest) {

		checkIfCityIdExists(updateCityRequest.getId());
		return new SuccessResult("Updated City");
	}

	@Override
	public DataResult<GetListCityDto> getById(short id) {

		checkIfCityIdExists(id);
		City city = this.cityDao.getById(id);
		GetListCityDto response = this.modelMapperService.forDto().map(city, GetListCityDto.class);

		return new SuccessDataResult<GetListCityDto>(response);
	}

	@Override
	public DataResult<List<GetListCityDto>> getAll() {

		List<City> cities = this.cityDao.findAll();
		List<GetListCityDto> response = cities.stream()
				.map(city -> this.modelMapperService.forDto().map(city, GetListCityDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetListCityDto>>(response, "Listed Cities");
	}

	@Override
	public DataResult<GetListCityDto> getByNameCity(String name) {

		isCityExists(name);

		City city = this.cityDao.getByCityName(name);
		GetListCityDto response = this.modelMapperService.forDto().map(city, GetListCityDto.class);

		return new SuccessDataResult<GetListCityDto>(response);
	}

	private boolean isCityExists(String name) {

		City city = this.cityDao.getByCityName(name);
		if (city != null) {
			return true;
		}
		throw new BusinessException("This city name doesnt found");
	}

	private boolean isCityNameExists(String name) {

		City city = this.cityDao.getByCityName(name);
		if (city == null) {
			return true;
		}
		throw new BusinessException("This city name already exists");
	}

	@Override
	public boolean checkIfCityIdExists(short id) {

		if (this.cityDao.existsById(id)) {
			return true;
		}
		throw new BusinessException("This city id doesnt found");
	}

}
