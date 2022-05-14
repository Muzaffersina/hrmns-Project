package com.kodlamaio.hrmns.hrmns.core.adapters.email;

import org.springframework.stereotype.Service;

import com.kodlamaio.hrmns.hrmns.core.concretes.BusinessException;
import com.kodlamaio.hrmns.hrmns.core.results.Result;
import com.kodlamaio.hrmns.hrmns.core.results.SuccessResult;
import com.kodlamaio.hrmns.hrmns.entities.UserEntity;

@Service
public class EmailValidationFakeManager implements EmailValidatonFakeService {

	private String title = "Email Activation";
	private String body = "Email body";
	
	@Override
	public Result fakeEmailValidation(UserEntity user , boolean status) {
		EmailActivation email = new EmailActivation();
		if(email.sendEmail(user.getEmail(),title , body)   && status) {
			
			return new SuccessResult("This email verified");
		}
		throw new BusinessException("This email has not been verified yet");
	}

}
