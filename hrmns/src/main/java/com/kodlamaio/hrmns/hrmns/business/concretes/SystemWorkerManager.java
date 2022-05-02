package com.kodlamaio.hrmns.hrmns.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrmns.hrmns.business.abstracts.EmployerService;
import com.kodlamaio.hrmns.hrmns.business.abstracts.JobService;
import com.kodlamaio.hrmns.hrmns.business.abstracts.SystemWorkerService;
import com.kodlamaio.hrmns.hrmns.business.dtos.GetListSystemWorkerDto;
import com.kodlamaio.hrmns.hrmns.business.requests.create.CreateSystemWorkerRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.delete.DeleteSystemWorkerRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.update.UpdateSystemWorkerRequest;
import com.kodlamaio.hrmns.hrmns.core.concretes.BusinessException;
import com.kodlamaio.hrmns.hrmns.core.mapping.ModelMapperService;
import com.kodlamaio.hrmns.hrmns.core.results.DataResult;
import com.kodlamaio.hrmns.hrmns.core.results.Result;
import com.kodlamaio.hrmns.hrmns.core.results.SuccessDataResult;
import com.kodlamaio.hrmns.hrmns.core.results.SuccessResult;
import com.kodlamaio.hrmns.hrmns.dataAccess.abstracts.SystemWorkerDao;
import com.kodlamaio.hrmns.hrmns.entities.SystemWorker;

@Service
public class SystemWorkerManager implements SystemWorkerService {

	private SystemWorkerDao systemWorkerDao;
	private ModelMapperService modelMapperService;
	private EmployerService employerService;
	private JobService jobService;

	@Autowired
	public SystemWorkerManager(SystemWorkerDao systemWorkerDao, ModelMapperService modelMapperService,
			EmployerService employerService) {
		this.systemWorkerDao = systemWorkerDao;
		this.modelMapperService = modelMapperService;
		this.employerService = employerService;
	}

	@Override
	public Result add(CreateSystemWorkerRequest createSystemWorkerRequest) {

		SystemWorker systemWorker = this.modelMapperService.forDto().map(createSystemWorkerRequest, SystemWorker.class);
		this.systemWorkerDao.save(systemWorker);
		systemWorker.setRole("systemWorker");

		return new SuccessResult("Created SystemWorker");
	}

	@Override
	public Result delete(DeleteSystemWorkerRequest deleteSystemWorkerRequest) {

		checkIfSystemWorkerId(deleteSystemWorkerRequest.getSystemWorkerId());
		this.systemWorkerDao.deleteById(deleteSystemWorkerRequest.getSystemWorkerId());
		return new SuccessResult("Deleted SystemWorker");
	}

	@Override
	public Result update(UpdateSystemWorkerRequest updateSystemWorkerRequest) {

		checkIfSystemWorkerId(updateSystemWorkerRequest.getSystemWorkerId());

		return null;
	}

	@Override
	public DataResult<List<GetListSystemWorkerDto>> getAll() {

		List<SystemWorker> systemWorkers = this.systemWorkerDao.findAll();
		List<GetListSystemWorkerDto> response = systemWorkers.stream()
				.map(systemWorker -> this.modelMapperService.forDto().map(systemWorker, GetListSystemWorkerDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetListSystemWorkerDto>>(response, "Listed SystemWorkers");
	}

	@Override
	public DataResult<GetListSystemWorkerDto> getBySystemWorkerId(int systemWorkerId) {

		checkIfSystemWorkerId(systemWorkerId);

		SystemWorker systemWorker = this.systemWorkerDao.getById(systemWorkerId);
		GetListSystemWorkerDto response = this.modelMapperService.forDto().map(systemWorker,
				GetListSystemWorkerDto.class);
		return new SuccessDataResult<GetListSystemWorkerDto>(response, "Listed SystemWorker");
	}

	@Override
	public Result validateEmployerId(int id, boolean status) {

		this.employerService.validateEmployerId(id, status);
		return new SuccessResult("This employer has been verified");
	}

	@Override
	public Result jobStatusManuelChange(int jobId, boolean status) {
		
		this.jobService.jobStatusManuelChange(jobId, status);
		return new SuccessResult("Job Status changed");
	}

	private boolean checkIfSystemWorkerId(int systemWorkerId) {

		if (this.systemWorkerDao.existsById(systemWorkerId)) {
			return true;
		}
		throw new BusinessException("This systemWorker id not found");
	}

}
