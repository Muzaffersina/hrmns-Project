package com.kodlamaio.hrmns.hrmns.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "persons")
@PrimaryKeyJoinColumn(name = "person_id", referencedColumnName = "id")
public class Person extends UserEntity {

	@Column(name = "person_id", insertable = false, updatable = false)
	private int personId;

	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "birth_date")
	private int birthDate;

	@Column(name = "social_security_number")
	private String socialSecurityNumber;
}
