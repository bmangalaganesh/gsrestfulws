package com.anz.acorn.mts.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anz.acorn.mts.domain.TestGreeting;

@RestController
public class TestGreetingController {

	@RequestMapping("/hateoastest")
	public ResponseEntity<TestGreeting> sayHello() {

		TestGreeting aGreeting = new TestGreeting("Hello World");
		aGreeting.add(linkTo(methodOn(TestGreetingController.class).sayHello()).withSelfRel());

		return new ResponseEntity<TestGreeting>(aGreeting, HttpStatus.OK);
	}
}
