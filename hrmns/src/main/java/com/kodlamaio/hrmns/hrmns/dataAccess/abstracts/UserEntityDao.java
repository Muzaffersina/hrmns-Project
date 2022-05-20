package com.kodlamaio.hrmns.hrmns.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodlamaio.hrmns.hrmns.entities.UserEntity;

@Repository
public interface UserEntityDao extends JpaRepository<UserEntity, Integer>{
	
	UserEntity getByEmail(String email);
}
