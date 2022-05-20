package com.kodlamaio.hrmns.hrmns.dataAccess.abstracts;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodlamaio.hrmns.hrmns.entities.JobAdvert;

@Repository
public interface JobAdvertDao extends JpaRepository<JobAdvert,Integer>{

	List<JobAdvert> getByEmployer_EmployerId(int employerId);
	List<JobAdvert> getByStatus(boolean status);
	List<JobAdvert> getByEmployer_EmployerIdAndStatus(int employerId,boolean status);
	List<JobAdvert> getByDeadLineBetween(LocalDate startDate, LocalDate endDate);
}
