package com.anz.acorn.mts.domain;

public class Address {

	private String streetName;

	public Address(String streetName) {

		this.streetName = streetName;

	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

}
