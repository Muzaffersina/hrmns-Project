package com.kodlamaio.hrmns.hrmns.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodlamaio.hrmns.hrmns.entities.Employer;

@Repository
public interface EmployerDao extends JpaRepository<Employer, Integer>{
	
	Employer getByEmployerIdAndSystemWorkerValidate(int employerId, boolean systemWorkerValidate);
}
