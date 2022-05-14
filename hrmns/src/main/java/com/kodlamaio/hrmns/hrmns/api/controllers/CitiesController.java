package com.kodlamaio.hrmns.hrmns.api.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.hrmns.hrmns.business.abstracts.CityService;
import com.kodlamaio.hrmns.hrmns.business.dtos.GetListCityDto;
import com.kodlamaio.hrmns.hrmns.core.results.DataResult;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {

	private CityService cityService;

	public CitiesController(CityService cityService) {
		this.cityService = cityService;
	}
	

	@PostMapping("/getByCityName")
	public DataResult<GetListCityDto> getByCityName(String cityName){
		return this.cityService.getByNameCity(cityName);
	}
}
