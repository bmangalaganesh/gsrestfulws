package com.anz.acorn.mts.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anz.acorn.mts.domain.Greeting;
import com.anz.acorn.mts.persistence.GreetingRepository;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";

	private final AtomicLong counter = new AtomicLong();

	private Greeting aGreeting = null;

	@Autowired
	GreetingRepository repository;

	@RequestMapping(value = "/greeting", method = { RequestMethod.GET }, produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public List<Greeting> getAGreeting(@RequestParam(value = "id") Long id) {

		// If id is null then return the list...
		List<Greeting> listOfGreetings = new ArrayList<Greeting>();
		repository.findAll().forEach(listOfGreetings::add); //how does this new code struture work.. Read about this TODO

		return listOfGreetings;

		// return new Greeting(counter.incrementAndGet(),
		// String.format(template, name), "testing only");
	}

	/**
	 * Get the HTTP Post body and have that automatically marshalled as a
	 * object...
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/greeting", method = { RequestMethod.POST }, produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public Greeting createAGreeting(@RequestBody Greeting greeting) {

		System.out.println("Greeting object that is received is:(before)" + greeting);
		greeting = repository.save(greeting);
		System.out.println("Greeting object that is received is:(after)" + greeting);
		return greeting;
	}

	/**
	 * Using this method to handle the update operation..
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/greeting", method = { RequestMethod.PUT }, produces = { MediaType.ALL_VALUE })
	public String updateGreeting(@RequestParam(value = "newparamname", defaultValue = "World") String name) {

		return "put action was executed";
	}

	/**
	 * Using this method to handle the delete operation..
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/greeting", method = { RequestMethod.DELETE }, produces = { MediaType.ALL_VALUE })
	public String deleteGreeting(@RequestParam(value = "newparamname", defaultValue = "World") String name) {

		return "delete action was executed";
	}

}
