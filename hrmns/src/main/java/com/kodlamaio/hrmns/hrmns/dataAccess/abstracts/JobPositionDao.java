package com.kodlamaio.hrmns.hrmns.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.hrmns.hrmns.entities.JobPosition;

public interface JobPositionDao extends JpaRepository<JobPosition, Short>{
	
	JobPosition getByName(String name);

}
