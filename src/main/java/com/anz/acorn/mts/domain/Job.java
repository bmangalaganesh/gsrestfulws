package com.anz.acorn.mts.domain;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This represents the Job Entity
 * This data is stored in the Database as well as sent across in a response call to the user (as JSON or XML Payload)
 * 
 * @author Manglu
 *
 */

@XmlRootElement(name = "aJob")
public class Job {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int jobId;

	private String userId;

	private String customerName;

	private String description;

	private String status;

	private Date dateCreated;

	private String invoiceNumber;

	private Date invoiceDateIssued;

	private Date invoiceDueDate;

	private Date invoiceDatePaid;

	// Unsure what this field is for...
	private int invoiceIncGst;

	//Default Constructor is required for JPA entities
	public Job() {
		super();
		
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public Date getInvoiceDateIssued() {
		return invoiceDateIssued;
	}

	public void setInvoiceDateIssued(Date invoiceDateIssued) {
		this.invoiceDateIssued = invoiceDateIssued;
	}

	public Date getInvoiceDueDate() {
		return invoiceDueDate;
	}

	public void setInvoiceDueDate(Date invoiceDueDate) {
		this.invoiceDueDate = invoiceDueDate;
	}

	public Date getInvoiceDatePaid() {
		return invoiceDatePaid;
	}

	public void setInvoiceDatePaid(Date invoiceDatePaid) {
		this.invoiceDatePaid = invoiceDatePaid;
	}

	public int getInvoiceIncGst() {
		return invoiceIncGst;
	}

	public void setInvoiceIncGst(int invoiceIncGst) {
		this.invoiceIncGst = invoiceIncGst;
	}

}
