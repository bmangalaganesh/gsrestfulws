package com.anz.acorn.mts.domain;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TestGreeting extends ResourceSupport {

	private String content;
	private Address homeAddress ;

	@JsonCreator
	public TestGreeting(@JsonProperty("content") String content) {
		super();
		this.content = content;
		
		this.homeAddress = new Address();
		homeAddress.setStreetName("Wall Street");
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

}
