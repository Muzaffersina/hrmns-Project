package com.kodlamaio.hrmns.hrmns.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kodlamaio.hrmns.hrmns.business.abstracts.PersonService;
import com.kodlamaio.hrmns.hrmns.business.abstracts.UserEntityService;
import com.kodlamaio.hrmns.hrmns.business.dtos.GetListPersonDto;
import com.kodlamaio.hrmns.hrmns.business.requests.create.CreatePersonRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.delete.DeletePersonRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.update.UpdatePersonRequest;
import com.kodlamaio.hrmns.hrmns.core.adapters.mernis.PersonCheckAdapterService;
import com.kodlamaio.hrmns.hrmns.core.concretes.BusinessException;
import com.kodlamaio.hrmns.hrmns.core.mapping.ModelMapperService;
import com.kodlamaio.hrmns.hrmns.core.results.DataResult;
import com.kodlamaio.hrmns.hrmns.core.results.Result;
import com.kodlamaio.hrmns.hrmns.core.results.SuccessDataResult;
import com.kodlamaio.hrmns.hrmns.core.results.SuccessResult;
import com.kodlamaio.hrmns.hrmns.dataAccess.abstracts.PersonDao;
import com.kodlamaio.hrmns.hrmns.entities.Person;

@Service
public class PersonManager implements PersonService {

	private PersonDao personDao;
	private ModelMapperService modelMapperService;
	private UserEntityService userService;
	private PersonCheckAdapterService personCheckAdapterService;
	
	@Autowired
	public PersonManager(PersonDao personDao, ModelMapperService modelMapperService, UserEntityService userService,
			PersonCheckAdapterService personCheckAdapterService) {
		this.personDao = personDao;
		this.modelMapperService = modelMapperService;
		this.userService = userService;
		this.personCheckAdapterService = personCheckAdapterService;

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = BusinessException.class)
	public Result add(CreatePersonRequest createPersonRequest) {

		checkIfEmailExists(createPersonRequest.getEmail());
		checkIfSocialSecurityNumberExists(createPersonRequest.getSocialSecurityNumber());

		Person person = this.modelMapperService.forDto().map(createPersonRequest, Person.class);
		//checkIfRealPerson(person);
		person.setRole("person");
		
		this.personDao.save(person);
		validateEmail(person.getId(), true);
		
		return new SuccessResult("Created Person");
	}

	@Override
	public Result delete(DeletePersonRequest deletePersonRequest) {
		
		checkIfPersonExists(deletePersonRequest.getPersonId());
		this.personDao.deleteById(deletePersonRequest.getPersonId());
		return new SuccessResult("Deleted Person");
	}

	@Override
	public Result update(UpdatePersonRequest updatePersonRequest) {
		checkIfPersonExists(updatePersonRequest.getPersonId());
		return new SuccessResult("Updated Person");
	}

	@Override
	public DataResult<List<GetListPersonDto>> getAll() {

		List<Person> persons = this.personDao.findAll();
		List<GetListPersonDto> response = persons.stream()
				.map(person -> this.modelMapperService.forDto().map(person, GetListPersonDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetListPersonDto>>(response, "Listed Person");
	}

	@Override
	public DataResult<GetListPersonDto> getByPersonId(int personId) {
		
		checkIfPersonExists(personId);
		
		Person person = this.personDao.getById(personId);		
		GetListPersonDto response = this.modelMapperService.forDto().map(person, GetListPersonDto.class);
		
		return new SuccessDataResult<GetListPersonDto>(response, "Listed Person");
		
	}

	private boolean checkIfEmailExists(String email) {

		if (this.userService.checkIfEmailExists(email)) {
			return true;
		}
		throw new BusinessException("This email already exists");
	}

	private boolean checkIfSocialSecurityNumberExists(String socialSecurityNumber) {

		if (this.personDao.getBySocialSecurityNumber(socialSecurityNumber) == null) {
			return true;
		}
		throw new BusinessException("This socialSecurityNumber already exists");
	}

	private void validateEmail(int id, boolean status) {
		this.userService.validateEmail(id, status);
	}
	
	private boolean checkIfPersonExists(int id) {
		
		if(this.personDao.existsById(id)) {
			return true;
		}
		throw new BusinessException("This Person id not found");
	}
	
	private boolean checkIfRealPerson(Person person) {
		
		if(this.personCheckAdapterService.isPersonExist(person).isSuccess()) {
			return true;
		}
		return false;
	}

}
