package com.anz.acorn.mts.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hello.Greeting;

@RestController
public class GreetingController {
	
	
	  private static final String template = "Hello, %s!";
	
	    private final AtomicLong counter = new AtomicLong();
	    
	    private Greeting aGreeting = null;
	    

	    @RequestMapping(value="/greeting",method={RequestMethod.POST,RequestMethod.GET},produces= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	    public Greeting greeting(@RequestParam(value="newparamname", defaultValue="World") String name) {
	        return new Greeting(counter.incrementAndGet(),
	                            String.format(template, name));
	    }


	    

	    
}
