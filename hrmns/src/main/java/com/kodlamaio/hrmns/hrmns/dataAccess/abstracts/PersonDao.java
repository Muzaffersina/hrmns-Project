package com.kodlamaio.hrmns.hrmns.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodlamaio.hrmns.hrmns.entities.Person;

@Repository

public interface PersonDao extends JpaRepository<Person, Integer>{
	
	Person getBySocialSecurityNumber(String socialSecurityNumber);

}
