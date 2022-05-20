package com.kodlamaio.hrmns.hrmns.business.abstracts;

import java.time.LocalDate;
import java.util.List;

import com.kodlamaio.hrmns.hrmns.business.dtos.GetListJobAdvertDto;
import com.kodlamaio.hrmns.hrmns.business.requests.create.CreateJobAdvertRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.delete.DeleteJobAdvertRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.update.UpdateJobAdvertRequest;
import com.kodlamaio.hrmns.hrmns.core.results.DataResult;
import com.kodlamaio.hrmns.hrmns.core.results.Result;

public interface JobAdvertService {
	
	Result add(CreateJobAdvertRequest createJobRequest);
	Result delete(DeleteJobAdvertRequest deleteJobRequest);
	Result update(UpdateJobAdvertRequest updateJobRequest);
	
	DataResult<List<GetListJobAdvertDto>> getAll();
	DataResult<List<GetListJobAdvertDto>> getByEmployerId(int employerId);
	DataResult<List<GetListJobAdvertDto>> getAllByJobStatusTrue();
	DataResult<List<GetListJobAdvertDto>> getJobStatusTrueByEmployer(int employerId);
	DataResult<List<GetListJobAdvertDto>> getAllJobStatusTrueByDate(LocalDate startDate , LocalDate endDate);
	
	void jobStatusManuelChange(int jobId,boolean status);
	Result closeJobStatusByEmployer(int employerId ,int jobId,boolean status);
}
