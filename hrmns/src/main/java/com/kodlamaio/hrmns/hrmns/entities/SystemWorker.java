package com.kodlamaio.hrmns.hrmns.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.PrimaryKeyJoinColumns;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "system_worker")
@PrimaryKeyJoinColumn(name = "system_worker_id", referencedColumnName = "id")

public class SystemWorker extends UserEntity{
	
	@Column(name = "system_worker_id", insertable = false, updatable = false)
	private int systemWorkerId;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "position")
	private String position;
}
