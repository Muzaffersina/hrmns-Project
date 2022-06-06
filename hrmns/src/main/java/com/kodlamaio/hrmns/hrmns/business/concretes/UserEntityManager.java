package com.kodlamaio.hrmns.hrmns.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrmns.hrmns.business.abstracts.UserEntityService;
import com.kodlamaio.hrmns.hrmns.business.dtos.GetListUserEntityDto;
import com.kodlamaio.hrmns.hrmns.core.adapters.email.EmailValidatonFakeService;
import com.kodlamaio.hrmns.hrmns.core.concretes.BusinessException;
import com.kodlamaio.hrmns.hrmns.core.mapping.ModelMapperService;
import com.kodlamaio.hrmns.hrmns.core.results.DataResult;
import com.kodlamaio.hrmns.hrmns.core.results.SuccessDataResult;
import com.kodlamaio.hrmns.hrmns.dataAccess.abstracts.UserEntityDao;
import com.kodlamaio.hrmns.hrmns.entities.UserEntity;

@Service
public class UserEntityManager implements UserEntityService {

	private UserEntityDao userEntityDao;
	private ModelMapperService modelMapperService;
	private EmailValidatonFakeService emailValidatonFakeService;

	@Autowired
	public UserEntityManager(UserEntityDao userEntityDao, ModelMapperService modelMapperService,
			EmailValidatonFakeService emailValidatonFakeService) {
		this.userEntityDao = userEntityDao;
		this.modelMapperService = modelMapperService;
		this.emailValidatonFakeService = emailValidatonFakeService;
	}

	@Override
	public DataResult<List<GetListUserEntityDto>> getAll() {

		List<UserEntity> users = this.userEntityDao.findAll();
		List<GetListUserEntityDto> response = users.stream()
				.map(user -> this.modelMapperService.forDto().map(user, GetListUserEntityDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetListUserEntityDto>>(response, "Listed Users");
	}

	@Override
	public DataResult<GetListUserEntityDto> getByUserId(int userId) {

		checkIfUserIdExists(userId);
		UserEntity user = this.userEntityDao.getById(userId);
		GetListUserEntityDto response = this.modelMapperService.forDto().map(user, GetListUserEntityDto.class);
		return new SuccessDataResult<GetListUserEntityDto>(response, "Listed User");
	}

	private boolean checkIfUserIdExists(int id) {

		if (this.userEntityDao.existsById(id)) {
			return true;
		}
		throw new BusinessException("This user id not found");
	}

	@Override
	public boolean checkIfEmailExists(String email) {
		
		UserEntity user = this.userEntityDao.getByEmail(email);
		if ( user== null) {
			return true;
		}
		return false;
	}

	@Override
	public void validateEmail(int id, boolean status) {

		checkIfUserIdExists(id);
		UserEntity user = this.userEntityDao.getById(id);
		this.emailValidatonFakeService.fakeEmailValidation(user, status);
	}
}
