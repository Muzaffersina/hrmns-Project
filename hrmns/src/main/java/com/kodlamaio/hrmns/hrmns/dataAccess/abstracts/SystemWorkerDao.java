package com.kodlamaio.hrmns.hrmns.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodlamaio.hrmns.hrmns.entities.SystemWorker;

@Repository
public interface SystemWorkerDao extends JpaRepository<SystemWorker, Integer> {

}
