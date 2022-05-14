package com.kodlamaio.hrmns.hrmns.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.hrmns.hrmns.entities.City;

public interface CityDao extends JpaRepository<City,Short> {
	City getByCityName(String cityName);
}
