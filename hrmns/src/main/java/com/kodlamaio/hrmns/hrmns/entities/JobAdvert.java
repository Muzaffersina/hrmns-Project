package com.kodlamaio.hrmns.hrmns.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "job_adverts")
public class JobAdvert {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "job_id")
	private int id;

	@Column(name = "job_info")
	private String jobInfo;

	@Column(name = "min_expected_salary")
	private int minExpectedSalary;

	@Column(name = "max_expected_salary")
	private int maxExpectedSalary;

	@Column(name = "number_of_Positions")
	private int numberOfPositions;

	@Column(name = "create_date")
	private LocalDate createDate;

	@Column(name = "dead_line")
	private LocalDate deadLine;

	@Column(name = "status")
	private boolean status;

	@JoinColumn(name = "job_position_id")
	@ManyToOne
	private JobPosition jobPosition;

	@JoinColumn(name = "city_id")
	@ManyToOne
	private City city;

	@JoinColumn(name = "working_time_id")
	@ManyToOne
	private WorkingTime workingTime;

	@JoinColumn(name = "working_type_id")
	@ManyToOne
	private WorkingType workingType;

	@ManyToOne()
	@JoinColumn(name = "employer_id")
	private Employer employer;
}
