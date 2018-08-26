package com.capgemini.types;

import java.util.Set;

import model.Address;
import model.ContactDetails;


public class ClientTO {

	private Long id;
	private Set<Long> flats;
	private String firstName;
	private String lastName;
	private Address address;
	private ContactDetails contact;
	
	
	public ClientTO(){
		
	}
	
	/**
	 * @param firstName
	 * @param lastName
	 */
	public ClientTO(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public ClientTO(String firstName, String lastName, Address address, ContactDetails contact) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.contact = contact;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Set<Long> getFlats() {
		return flats;
	}
	public void setFlats(Set<Long> flats) {
		this.flats = flats;
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
	
	
	
}
