package com.kodlamaio.hrmns.hrmns.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.hrmns.hrmns.business.abstracts.PersonService;
import com.kodlamaio.hrmns.hrmns.business.dtos.GetListPersonDto;
import com.kodlamaio.hrmns.hrmns.business.requests.create.CreatePersonRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.delete.DeletePersonRequest;
import com.kodlamaio.hrmns.hrmns.core.results.DataResult;
import com.kodlamaio.hrmns.hrmns.core.results.Result;

@RestController
@RequestMapping("/api/persons")
public class PersonsController {

	private PersonService personService;

	@Autowired
	public PersonsController(PersonService personService) {
		this.personService = personService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreatePersonRequest createPersonRequest) {
		return this.personService.add(createPersonRequest);
	}

	@DeleteMapping("/delete")
	public Result add(@RequestBody @Valid DeletePersonRequest deletePersonRequest) {
		return this.personService.delete(deletePersonRequest);
	}

	@GetMapping("/getByPersonId")
	public DataResult<GetListPersonDto> getByPersonId(int personId) {
		return this.personService.getByPersonId(personId);
	}

	@GetMapping("/getAll")
	public DataResult<List<GetListPersonDto>> getAll() {
		return this.personService.getAll();
	}
}
