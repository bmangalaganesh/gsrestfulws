package com.anz.acorn.mts.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * This is the class that is sent to the client (as either a JSON or XML)
 * 
 * To ensure that XML content is produced we need to specify the root element
 * annotation
 * 
 * @author Manglu
 *
 */

@Entity
public class Greeting {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String content;

	//
	/**
	 * Creating a default constructor.
	 */

	public Greeting() {
		this.id = 1;
		content = "default";

	}

	public Greeting(long id, String content) {
		this.id = id;
		this.content = content;

	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return " Greeting info is id:" + id + " content:" + content;

	}

	public void setContent(String content) {
		this.content = content;
	}

}
