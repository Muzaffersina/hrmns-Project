package com.kodlamaio.hrmns.hrmns.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cities")
public class City {
	@Id
	@Column(name = "city_id")
	private short id;
	
	@Column(name = "city_name")
	private String cityName;

}
