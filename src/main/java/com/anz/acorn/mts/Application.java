package com.anz.acorn.mts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;

import com.anz.acorn.mts.persistence.JobsRepository;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan(basePackages={"com.anz.acorn.mts.domain"})
public class Application {
	
	@Autowired
	private static JobsRepository repository;

    public static void main(String[] args) {
    	
    	//Load up and initialize the data in the database....
    	initDatabase(repository);
    	
    	//Now launch the sprint boot stuff..
    	//I still need to figure out how this functions...
        SpringApplication.run(Application.class, args);
        
    }
    
    
    private static void initDatabase(JobsRepository repository){
    	
    	// save a couple of customers
    				//repository.save(new Jobs("Jack", "Bauer"));
    				//repository.save(new Jobs("Chloe", "O'Brian"));
    }
  
}