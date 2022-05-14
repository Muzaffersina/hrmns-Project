package com.kodlamaio.hrmns.hrmns.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kodlamaio.hrmns.hrmns.business.abstracts.EmployerService;
import com.kodlamaio.hrmns.hrmns.business.abstracts.JobService;
import com.kodlamaio.hrmns.hrmns.business.abstracts.UserService;
import com.kodlamaio.hrmns.hrmns.business.dtos.GetListEmployerDto;
import com.kodlamaio.hrmns.hrmns.business.requests.create.CreateEmployerRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.delete.DeleteEmployerRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.update.UpdateEmployerRequest;
import com.kodlamaio.hrmns.hrmns.core.concretes.BusinessException;
import com.kodlamaio.hrmns.hrmns.core.mapping.ModelMapperService;
import com.kodlamaio.hrmns.hrmns.core.results.DataResult;
import com.kodlamaio.hrmns.hrmns.core.results.Result;
import com.kodlamaio.hrmns.hrmns.core.results.SuccessDataResult;
import com.kodlamaio.hrmns.hrmns.core.results.SuccessResult;
import com.kodlamaio.hrmns.hrmns.dataAccess.abstracts.EmployerDao;
import com.kodlamaio.hrmns.hrmns.entities.Employer;

@Service
public class EmployerManager implements EmployerService {

	private EmployerDao employerDao;
	private ModelMapperService modelMapperService;
	private UserService userService;
	private JobService jobService;

	@Autowired
	@Lazy
	public EmployerManager(EmployerDao employerDao, ModelMapperService modelMapperService, UserService userService,
			JobService jobService) {
		this.employerDao = employerDao;
		this.modelMapperService = modelMapperService;
		this.userService = userService;
		this.jobService = jobService;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = BusinessException.class)
	public Result add(CreateEmployerRequest createEmployerRequest) {

		checkIfEmailExists(createEmployerRequest.getEmail());

		Employer employer = this.modelMapperService.forDto().map(createEmployerRequest, Employer.class);
		employer.setRole("employer");

		this.employerDao.save(employer);
		validateEmail(employer.getId(), true);

		return new SuccessResult("Created Employer");
	}

	@Override
	public Result delete(DeleteEmployerRequest deleteEmployerRequest) {

		checkIfEmployerExists(deleteEmployerRequest.getEmployerId());

		this.employerDao.deleteById(deleteEmployerRequest.getEmployerId());

		return new SuccessResult("Deleted Employer");
	}

	@Override
	public Result update(UpdateEmployerRequest updateEmployerRequest) {

		checkIfEmployerExists(updateEmployerRequest.getEmployerId());

		return new SuccessResult("Updated Employer");
	}

	@Override
	public DataResult<List<GetListEmployerDto>> getAll() {

		List<Employer> employers = this.employerDao.findAll();
		List<GetListEmployerDto> response = employers.stream()
				.map(employer -> this.modelMapperService.forDto().map(employer, GetListEmployerDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetListEmployerDto>>(response, "Listed Employers");
	}

	@Override
	public DataResult<GetListEmployerDto> getByEmployerId(int employerId) {

		checkIfEmployerExists(employerId);

		Employer employer = this.employerDao.getById(employerId);
		GetListEmployerDto response = this.modelMapperService.forDto().map(employer, GetListEmployerDto.class);
		return new SuccessDataResult<GetListEmployerDto>(response, "Listed Employer");

	}

	@Override
	public void validateEmployerId(int id, boolean status) {

		checkIfEmployerExists(id);
		Employer employer = this.employerDao.getById(id);
		employer.setSystemWorkerValidate(status);
		this.employerDao.save(employer);
	}

	@Override
	public Result closeJobStatus(int employerId, int jobId) {

		this.jobService.closeJobStatusByEmployer(employerId, jobId, false);
		return new SuccessResult("This job has been closed");
	}

	private boolean checkIfEmailExists(String email) {

		if (this.userService.checkIfEmailExists(email)) {
			return true;
		}
		throw new BusinessException("This email already exists");

	}

	@Override
	public boolean checkIfEmployerExists(int employerId) {

		if (this.employerDao.existsById(employerId)) {
			return true;
		}
		throw new BusinessException("This EmployerId not found.");
	}

	private void validateEmail(int id, boolean status) {
		this.userService.validateEmail(id, status);
	}
}
