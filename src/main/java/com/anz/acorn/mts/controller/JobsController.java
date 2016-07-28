package com.anz.acorn.mts.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.anz.acorn.mts.domain.Job;
import com.anz.acorn.mts.persistence.JobsRepository;

@RestController
public class JobsController {

	@Autowired
	private JobsRepository repository;

	private static Logger log = LoggerFactory.getLogger(JobsController.class);

	// TODO remove this once I am satisifed that this is being called
	static {

		log.info("Is this ever called....");
	}

	/**
	 * Get the list of all Jobs
	 * 
	 * For the moment return all the jobs and later refine it to return only the
	 * Jobs for a specific customer
	 * 
	 * @return
	 */

	@RequestMapping(value = "/jobs", method = { RequestMethod.GET }, produces = { MediaType.ALL_VALUE })
	public List<Job> getAllJobs() {

		log.info("This appears to work well");

		List<Job> theList = null;

		// Iterable<Job> is returned and a cast is required here...
		try {
			theList = (List<Job>) repository.findAll();
		} finally {
			System.out.println("The list is:" + theList);
		}

		// new ArrayList<Job>();

		// theList.add(new Job());

		return theList;
		// return " All the jobs...";

	}

	/**
	 * How to conver t the incoming body as a Job Object.
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/jobs", method = { RequestMethod.POST }, produces = { MediaType.ALL_VALUE })
	public Job createJob(String userId) {

		log.info("Request received to create a job for userid: " + userId);

		Job aJob = null;

		aJob = repository.save(aJob);
		return aJob;

	}

}
