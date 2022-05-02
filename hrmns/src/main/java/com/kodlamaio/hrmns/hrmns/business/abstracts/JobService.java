package com.kodlamaio.hrmns.hrmns.business.abstracts;

import java.time.LocalDate;
import java.util.List;

import com.kodlamaio.hrmns.hrmns.business.dtos.GetListJobDto;
import com.kodlamaio.hrmns.hrmns.business.requests.create.CreateJobRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.delete.DeleteJobRequest;
import com.kodlamaio.hrmns.hrmns.business.requests.update.UpdateJobRequest;
import com.kodlamaio.hrmns.hrmns.core.results.DataResult;
import com.kodlamaio.hrmns.hrmns.core.results.Result;

public interface JobService {
	
	Result add(CreateJobRequest createJobRequest);
	Result delete(DeleteJobRequest deleteJobRequest);
	Result update(UpdateJobRequest updateJobRequest);
	
	DataResult<List<GetListJobDto>> getAll();
	DataResult<List<GetListJobDto>> getByEmployerId(int employerId);
	DataResult<List<GetListJobDto>> getAllByJobStatusTrue();
	DataResult<List<GetListJobDto>> getJobStatusTrueByEmployer(int employerId);
	DataResult<List<GetListJobDto>> getAllJobStatusTrueByDate(LocalDate startDate , LocalDate endDate);
	
	void jobStatusManuelChange(int jobId,boolean status);
	Result closeJobStatusByEmployer(int employerId ,int jobId,boolean status);
}
