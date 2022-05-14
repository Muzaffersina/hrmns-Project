package com.kodlamaio.hrmns.hrmns.core.adapters.mernis;

import org.springframework.stereotype.Service;

import com.kodlamaio.hrmns.hrmns.core.results.ErrorResult;
import com.kodlamaio.hrmns.hrmns.core.results.Result;
import com.kodlamaio.hrmns.hrmns.core.results.SuccessResult;
import com.kodlamaio.hrmns.hrmns.entities.Person;

import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;

@Service
public class PersonCheckAdapterManager implements PersonCheckAdapterService {
	

	@Override
	public Result isPersonExist(Person person) {
		
		KPSPublicSoapProxy kpsPublicSoapProxy = new KPSPublicSoapProxy();

		try {
			kpsPublicSoapProxy.TCKimlikNoDogrula(Long.parseLong(person.getSocialSecurityNumber()), person.getFirstName(),
					person.getLastName(), person.getBirthDate());
			System.out.println("successful validation");
			return new SuccessResult("successful validation");

		} catch (Exception exc) {
			exc.printStackTrace();
			System.out.println("failed validation");
			return new ErrorResult("failed validation");
		}
	}

}
