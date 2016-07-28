package com.anz.acorn.mts.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.anz.acorn.mts.domain.Greeting;

public interface GreetingRepository extends CrudRepository<Greeting, Long> {

	//public List<Greeting> retriveAllGreetings();

}
