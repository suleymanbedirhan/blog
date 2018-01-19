package com.blogger.blog.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "PERSON")
@XmlRootElement(name = "PERSON")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person implements java.io.Serializable {
	
	private static final long serialVersionUID = 7440297955003302414L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id")
    @SequenceGenerator(name = "id", sequenceName = "id")
	@Column(name="personId")
	private long personId;
	
	@Column(name="name", nullable = false, length=30)
	private String name;

	@Column(name="surname", nullable = false, length=30)
	private String surname;
		
	@Column(name="status", nullable = true, insertable = false, updatable = true)
    private String status;
	
	@Column(name="date_of_birth", nullable = true, insertable = false, updatable = true)
	private Date dateOfBirth;
	
	@Column(name="email", nullable = true, insertable = false, updatable = true)
	private String email;

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

}