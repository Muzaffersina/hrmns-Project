package com.kodlamaio.hrmns.hrmns.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employers")
@PrimaryKeyJoinColumn(name = "employer_id", referencedColumnName = "id")

public class Employer extends UserEntity {
	
	@Column(name = "employer_id", insertable = false, updatable = false)
	private int employerId;
	
	@Column(name = "company_name")
	private String companyName;
	
	@Column(name = "company_website")
	private String companyWebsite;
	
	@Column(name = "system_worker_validate")
	private boolean systemWorkerValidate;
	
	@OneToMany(mappedBy = "employer")
	private List<JobAdvert> jobs;
}
