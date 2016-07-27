package hello;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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
@XmlType(propOrder = {"id", "content"})
@JsonPropertyOrder({"content", "id"})
public class Greeting {

	@XmlElement
	private final long id;
	
	@XmlElement
	private final String content;

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

}
