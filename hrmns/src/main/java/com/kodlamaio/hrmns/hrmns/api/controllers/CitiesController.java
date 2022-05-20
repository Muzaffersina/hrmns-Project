package com.kodlamaio.hrmns.hrmns.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.hrmns.hrmns.business.abstracts.CityService;
import com.kodlamaio.hrmns.hrmns.business.dtos.GetListCityDto;
import com.kodlamaio.hrmns.hrmns.business.requests.create.CreateCityRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.delete.DeleteCityRequest;
import com.kodlamaio.hrmns.hrmns.core.results.DataResult;
import com.kodlamaio.hrmns.hrmns.core.results.Result;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {

	private CityService cityService;
	
	@Autowired
	public CitiesController(CityService cityService) {
		this.cityService = cityService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateCityRequest createCityRequest) {
		return this.cityService.add(createCityRequest);
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestBody @Valid DeleteCityRequest deleteCityRequest) {
		return this.cityService.delete(deleteCityRequest);
	}

	@GetMapping("/getByCityName")
	public DataResult<GetListCityDto> getByCityName(String cityName) {
		return this.cityService.getByNameCity(cityName);
	}

	@GetMapping("/getAllCity")
	public DataResult<List<GetListCityDto>> getAll() {
		return this.cityService.getAll();
	}

	@GetMapping("/getByCityId")
	public DataResult<GetListCityDto> getById(short id) {
		return this.cityService.getById(id);
	}
}
