package com.kodlamaio.hrmns.hrmns.dataAccess.abstracts;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodlamaio.hrmns.hrmns.entities.Job;

@Repository
public interface JobDao extends JpaRepository<Job,Integer>{

	List<Job> getByEmployer_EmployerId(int employerId);
	List<Job> getByStatus(boolean status);
	List<Job> getByEmployer_EmployerIdAndStatus(int employerId,boolean status);
	List<Job> getByDeadLineBetween(LocalDate startDate, LocalDate endDate);
}
