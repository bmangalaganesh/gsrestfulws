package com.anz.acorn.mts.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.anz.acorn.mts.domain.Greeting;
import com.anz.acorn.mts.persistence.GreetingRepository;

@RestController
public class GreetingController {

	private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);

	@Autowired
	GreetingRepository repository;

	@RequestMapping(value = "/greeting", method = { RequestMethod.GET }, produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Greeting>> getTheListOfGreetings() {

		// If id is null then return the list...
		List<Greeting> listOfGreetings = new ArrayList<Greeting>();
		repository.findAll().forEach(listOfGreetings::add);
		// how does this new code structure work.. Read about this TODO

		if (listOfGreetings.isEmpty()) {
			return new ResponseEntity<List<Greeting>>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Greeting>>(listOfGreetings, HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/greeting/{id}", method = { RequestMethod.GET }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })

	public ResponseEntity<Greeting> getASpecificGreeting(@PathVariable("id") long id) {

		Greeting theGreeting = repository.findOne(id);

		if (theGreeting != null) {
			return new ResponseEntity<Greeting>(theGreeting, HttpStatus.OK);
		} else {
			return new ResponseEntity<Greeting>(HttpStatus.NOT_FOUND);
		}

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

		logger.debug("Greeting object that is received is:(before)" + greeting);
		greeting = repository.save(greeting);
		logger.debug("Greeting object that is received is:(after)" + greeting);
		return greeting;
	}

	/**
	 * 
	 * Updates the greeting with the specified id.
	 * 
	 * @param id
	 * @param aGreeting
	 * @return
	 */
	@RequestMapping(value = "/greeting/{id}", method = { RequestMethod.PUT }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Greeting> updateGreeting(@PathVariable("id") long id, @RequestBody Greeting aGreeting) {

		Greeting greetingInDB = repository.findOne(id);
		if (greetingInDB != null) {

			// Udpate the attributes of greetingInDB with the incoming
			// attributes from the client (aGreeting)

			// Here we have only one attribute...
			greetingInDB.setContent(aGreeting.getContent());

			// Now update the greeetingInDB...

			Greeting updatedGreeting = repository.save(greetingInDB);
			return new ResponseEntity<Greeting>(updatedGreeting, HttpStatus.OK);

		} else {
			return new ResponseEntity<Greeting>(HttpStatus.NOT_FOUND);
		}

	}

	/**
	 * Using this method to handle the delete operation..
	 * 
	 * Delete the greeting with the specified id.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/greeting/{id}", method = { RequestMethod.DELETE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })

	public ResponseEntity<Greeting> deleteGreeting(@PathVariable("id") long id) {

		// Check if this Id exists before attempting the delete operation..

		logger.debug("Check if this resource exists. If it does not return a 404");

		Greeting theGreeting = repository.findOne(id);

		if (theGreeting != null) {
			logger.debug("Greeting with ID:" + id + "exists. Attempting deletion of this entity");
			repository.delete(theGreeting);
			logger.debug("Delete the entity successfully");
			return new ResponseEntity<Greeting>(HttpStatus.NO_CONTENT);
		} else {

			return new ResponseEntity<Greeting>(HttpStatus.NOT_FOUND);
		}

	}

}
