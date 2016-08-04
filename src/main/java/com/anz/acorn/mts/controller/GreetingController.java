package com.anz.acorn.mts.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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

import com.anz.acorn.mts.view.Greeting;
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
		List<com.anz.acorn.mts.domain.Greeting> listOfGreetings = new ArrayList<com.anz.acorn.mts.domain.Greeting>();
		repository.findAll().forEach(listOfGreetings::add);
		// how does this new code structure work.. Read about this TODO

		if (listOfGreetings.isEmpty()) {
			return new ResponseEntity<List<Greeting>>(HttpStatus.NOT_FOUND);
		} else {
			// Convert from the domain Greeting to the view Greeting here....
			return new ResponseEntity<List<Greeting>>(convertDomainGreetingListToViewGreetingList(listOfGreetings),
					HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/greeting/{id}", method = { RequestMethod.GET }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })

	public ResponseEntity<Greeting> getASpecificGreeting(@PathVariable("id") long id) {

		com.anz.acorn.mts.domain.Greeting theGreeting = repository.findOne(id);

		if (theGreeting != null) {

			// Copy the Greeting Entity Object to a corresponding view object
			// and then do the following on the view object to get the hateoas
			// links
			Greeting viewGreeting = new Greeting(theGreeting);
			viewGreeting.add(linkTo(methodOn(GreetingController.class).getASpecificGreeting(id)).withSelfRel());
			// TODO Convert from Entity Greeting to the view Greeting
			return new ResponseEntity<Greeting>(viewGreeting, HttpStatus.OK);
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
		com.anz.acorn.mts.domain.Greeting domainGreeting = new com.anz.acorn.mts.domain.Greeting();

		domainGreeting.setContent(greeting.getContent());

		domainGreeting = repository.save(domainGreeting);
		greeting = new Greeting(domainGreeting);

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

		com.anz.acorn.mts.domain.Greeting greetingInDB = repository.findOne(id);
		if (greetingInDB != null) {

			// Udpate the attributes of greetingInDB with the incoming
			// attributes from the client (aGreeting)

			// Here we have only one attribute...
			greetingInDB.setContent(aGreeting.getContent());

			// Now update the greeetingInDB...

			com.anz.acorn.mts.domain.Greeting updatedGreeting = repository.save(greetingInDB);
			return new ResponseEntity<Greeting>(new Greeting(updatedGreeting), HttpStatus.OK);

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

		com.anz.acorn.mts.domain.Greeting theGreeting = repository.findOne(id);

		if (theGreeting != null) {
			logger.debug("Greeting with ID:" + id + "exists. Attempting deletion of this entity");
			repository.delete(theGreeting);
			logger.debug("Delete the entity successfully");
			return new ResponseEntity<Greeting>(HttpStatus.NO_CONTENT);
		} else {

			return new ResponseEntity<Greeting>(HttpStatus.NOT_FOUND);
		}

	}

	/**
	 * COnverts the entity Greeting list to a view Greeting List so that it can
	 * be presented to teh view layer...
	 * 
	 * @param entityGreetingList
	 * @return
	 */

	private List<Greeting> convertDomainGreetingListToViewGreetingList(
			List<com.anz.acorn.mts.domain.Greeting> entityGreetingList) {

		List<Greeting> viewGreetingList = new ArrayList<Greeting>();

		for (com.anz.acorn.mts.domain.Greeting anEntityGreeting : entityGreetingList) {
			// create the view greeting based on the entity and add it to the
			// list.
			viewGreetingList.add(new Greeting(anEntityGreeting));
		}

		return viewGreetingList;

	}
}
