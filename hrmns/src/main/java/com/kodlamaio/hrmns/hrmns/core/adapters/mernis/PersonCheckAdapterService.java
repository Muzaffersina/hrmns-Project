package com.kodlamaio.hrmns.hrmns.core.adapters.mernis;

import com.kodlamaio.hrmns.hrmns.core.results.Result;
import com.kodlamaio.hrmns.hrmns.entities.Person;

public interface PersonCheckAdapterService {
	
	Result isPersonExist(Person person);

}
