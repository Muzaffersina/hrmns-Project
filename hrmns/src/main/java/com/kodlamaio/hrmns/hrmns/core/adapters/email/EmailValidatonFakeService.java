package com.kodlamaio.hrmns.hrmns.core.adapters.email;

import com.kodlamaio.hrmns.hrmns.core.results.Result;
import com.kodlamaio.hrmns.hrmns.entities.UserEntity;

public interface EmailValidatonFakeService {
	
	Result fakeEmailValidation(UserEntity user, boolean status);

}
