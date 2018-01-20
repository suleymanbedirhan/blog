package com.blogger.blog.view;

import java.util.Date;

import com.blogger.blog.model.Person;

/**
 * 
 * @author silemanbed
 *
 */
public class EntryVO {
	
	private Person person;
	
	private String entryTitle;
	
	private String entryDetail;
	
	private Date entryDate;

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	public String getEntryTitle() {
		return entryTitle;
	}

	public void setEntryTitle(String entryTitle) {
		this.entryTitle = entryTitle;
	}

	public String getEntryDetail() {
		return entryDetail;
	}

	public void setEntryDetail(String entryDetail) {
		this.entryDetail = entryDetail;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

}