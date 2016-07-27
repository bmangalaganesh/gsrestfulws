/**
 * 
 */
package com.anz.acorn.mts.persistence;

import org.springframework.data.repository.CrudRepository;

import com.anz.acorn.mts.domain.Job;

/**
 * @author Manglu
 *
 */
public interface JobsRepository extends CrudRepository<Job, Integer> {
	
		//Locate the Job based on the JobID. JobId is a key so at most a Job can be returned
		public Job findByJobId(Integer jobId);
		

	}


