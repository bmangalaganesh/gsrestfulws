package com.anz.acorn.mts.view;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.ResourceSupport;

import com.anz.acorn.mts.domain.Address;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This is the class that is sent to the client (as either a JSON or XML)
 * 
 * To ensure that XML content is produced we need to specify the root element
 * annotation
 * 
 * @author Manglu
 *
 */

@XmlRootElement(name = "greeting")
// @XmlType(propOrder = {"id", "content"})
// @JsonPropertyOrder({"content", "id"})

public class Greeting extends ResourceSupport {

	@XmlElement
	@JsonProperty("id")
	// the attribute id has a specific meaning in Resource Support. As we are
	// extending it we cannot use this. We will have to specify this
	// JsonProperty so that the marshalling/Unmarshalling to JSON would convert
	// them appropriately
	private long theId;

	@XmlElement
	private String content;

	@XmlElement
	private Address homeAddress;

	/**
	 * @JsonCreator
	 * @param content
	 * 
	 *            public TestGreeting(@JsonProperty("content") String content) {
	 *            super(); this.content = content;
	 * 
	 *            this.homeAddress = new Address(); homeAddress.setStreetName(
	 *            "Wall Street"); }
	 */

	//
	/**
	 * Creating a default constructor. Not having one results in an error when
	 * the payload was set as XML Could not instantiate JAXBContext for class
	 * [class hello.Greeting]: 1 counts of IllegalAnnotationExceptions; nested
	 * exception is
	 * com.sun.xml.internal.bind.v2.runtime.IllegalAnnotationsException: 1
	 * counts of IllegalAnnotationExceptions hello.Greeting does not have a
	 * no-arg default constructor. this problem is related to the following
	 * location: at hello.Greeting
	 */

	public Greeting() {
		this.theId = 1;
		content = "default";
	}

	public Greeting(com.anz.acorn.mts.domain.Greeting entityGreeting) {
		this.theId = entityGreeting.getId();
		this.content = entityGreeting.getContent();
	}

	public Greeting(long id, String content) {

		this(id, content, new Address("Wall Street"));
	}

	public Greeting(long id, String content, Address homeAddress) {
		this.theId = id;
		this.content = content;

		this.homeAddress = homeAddress;

	}

	public String getContent() {
		return content;
	}

	public void setTheId(long theId) {
		this.theId = theId;
	}

	@Override
	public String toString() {
		return " Greeting info is id:" + theId + " content:" + content;

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
