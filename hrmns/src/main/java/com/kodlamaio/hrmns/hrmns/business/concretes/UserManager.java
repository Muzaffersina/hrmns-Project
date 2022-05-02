package com.kodlamaio.hrmns.hrmns.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrmns.hrmns.business.abstracts.UserService;
import com.kodlamaio.hrmns.hrmns.business.dtos.GetListUserDto;
import com.kodlamaio.hrmns.hrmns.core.adapters.email.EmailValidatonFakeService;
import com.kodlamaio.hrmns.hrmns.core.concretes.BusinessException;
import com.kodlamaio.hrmns.hrmns.core.mapping.ModelMapperService;
import com.kodlamaio.hrmns.hrmns.core.results.DataResult;
import com.kodlamaio.hrmns.hrmns.core.results.SuccessDataResult;
import com.kodlamaio.hrmns.hrmns.dataAccess.abstracts.UserDao;
import com.kodlamaio.hrmns.hrmns.entities.UserEntity;

@Service
public class UserManager implements UserService {

	private UserDao userDao;
	private ModelMapperService modelMapperService;
	private EmailValidatonFakeService emailValidatonFakeService;

	@Autowired
	public UserManager(UserDao userDao, ModelMapperService modelMapperService,
			EmailValidatonFakeService emailValidatonFakeService) {
		this.userDao = userDao;
		this.modelMapperService = modelMapperService;
		this.emailValidatonFakeService = emailValidatonFakeService;
	}

	@Override
	public DataResult<List<GetListUserDto>> getAll() {

		List<UserEntity> users = this.userDao.findAll();
		List<GetListUserDto> response = users.stream()
				.map(user -> this.modelMapperService.forDto().map(user, GetListUserDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetListUserDto>>(response, "Listed Users");
	}

	@Override
	public DataResult<GetListUserDto> getByUserId(int userId) {

		checkIfUserIdExists(userId);
		UserEntity user = this.userDao.getById(userId);
		GetListUserDto response = this.modelMapperService.forDto().map(user, GetListUserDto.class);
		return new SuccessDataResult<GetListUserDto>(response, "Listed User");
	}

	private boolean checkIfUserIdExists(int id) {

		if (this.userDao.existsById(id)) {
			return true;
		}
		throw new BusinessException("This user id not found");
	}

	@Override
	public boolean checkIfEmailExists(String email) {
		
		UserEntity user = this.userDao.getByEmail(email);
		if ( user== null) {
			return true;
		}
		return false;
	}

	@Override
	public void validateEmail(int id, boolean status) {

		checkIfUserIdExists(id);
		UserEntity user = this.userDao.getById(id);
		this.emailValidatonFakeService.fakeEmailValidation(user, status);
	}
}
