package com.blogger.blog.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "ENTRY")
@XmlRootElement(name = "ENTRY")
@XmlAccessorType(XmlAccessType.FIELD)
public class Entry implements java.io.Serializable {
	
	private static final long serialVersionUID = 744029795223302414L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id")
    @SequenceGenerator(name = "id", sequenceName = "id")
	@Column(name="entryId")
	private long entryId;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Person.class)
	@JoinColumn(name = "personId", nullable = false)
	private Person person;
		
	@Column(name="entry_title")
	private String entryTitle;
	
	@Column(name="entry_detail")
	private String entryDetail;
	
	@Column(name="entry_date")
	private Date entryDate;
	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public long getEntryId() {
		return entryId;
	}

	public void setEntryId(long entryId) {
		this.entryId = entryId;
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

