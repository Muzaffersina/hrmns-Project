package com.kodlamaio.hrmns.hrmns.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.hrmns.hrmns.entities.WorkingType;

public interface WorkingTypeDao extends JpaRepository<WorkingType, Short>{
	
	WorkingType getByName(String name);
}
