package com.kodlamaio.hrmns.hrmns.core.adapters.email;

import org.springframework.stereotype.Service;

import com.kodlamaio.hrmns.hrmns.core.concretes.BusinessException;
import com.kodlamaio.hrmns.hrmns.core.results.Result;
import com.kodlamaio.hrmns.hrmns.core.results.SuccessResult;
import com.kodlamaio.hrmns.hrmns.entities.UserEntity;

@Service
public class EmailValidationFakeManager implements EmailValidatonFakeService {

	@Override
	public Result fakeEmailValidation(UserEntity user , boolean status) {
		if(status) {
			
			user.setValidation(true);
			return new SuccessResult("This email verified");
		}
		throw new BusinessException("This email has not been verified yet");
	}

}
