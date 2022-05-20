package com.kodlamaio.hrmns.hrmns.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.hrmns.hrmns.entities.WorkingTime;

public interface WorkingTimeDao extends JpaRepository<WorkingTime, Short>{

	WorkingTime getByName(String name);
}
