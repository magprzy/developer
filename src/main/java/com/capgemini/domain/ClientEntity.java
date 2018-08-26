package com.capgemini.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import com.capgemini.listeners.Auditable;
import com.capgemini.listeners.PreListeners;

import model.Address;
import model.ContactDetails;

@Entity
@Table (name = "Client")
@EntityListeners (PreListeners.class)
public class ClientEntity implements Auditable{

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
	
	
	@Embedded
	private Address address;
	
	
	@Embedded
	private ContactDetails contact;
	
	@ManyToMany (mappedBy = "clients")
	private Set<FlatEntity> flats = new HashSet<>();	
	
	@NotNull
	private Date dateCreated;
	
	private Date dateLastUpdated;
	
	@Version
	private Long version;
	
	
	public ClientEntity() {
		
	}
	
	/**
	 * @param firstName
	 * @param lastName
	 */
	public ClientEntity(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public ContactDetails getContact() {
		return contact;
	}
	public void setContact(ContactDetails contact) {
		this.contact = contact;
	}
	
	public Set<FlatEntity> getFlats() {
		return flats;
	}
	public void setFlats(Set<FlatEntity> flats) {
		this.flats = flats;
	}
	
	
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	@Override
	public void setDateCreated(Date date) {
		this.dateCreated = date;
		
	}
	@Override
	public Date getDateCreated() {
		return dateCreated;
	}
	@Override
	public void setDateLastUpdated(Date date) {
		this.dateLastUpdated = date;
		
	}
	@Override
	public Date getDateLastUpdated() {
		return dateLastUpdated;
	}
	
	
	
}
