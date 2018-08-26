package com.capgemini.types;

import java.util.Set;

import model.Address;

public class BuildingTO {

	private Long id;
	private String description;
	private Address address;
	private int numberOfFlors;
	boolean isLift;
	private Set<Long> flats;
	private int numberOfFlats;
	
	public BuildingTO(){
		
	}
	
	public BuildingTO(String description, Address address, int numberOfFlors, boolean isLift, Set<Long> flats,
			int numberOfFlats) {
		super();
		this.description = description;
		this.address = address;
		this.numberOfFlors = numberOfFlors;
		this.isLift = isLift;
		this.flats = flats;
		this.numberOfFlats = numberOfFlats;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public int getNumberOfFlors() {
		return numberOfFlors;
	}
	public void setNumberOfFlors(int numberOfFlors) {
		this.numberOfFlors = numberOfFlors;
	}
	public boolean isLift() {
		return isLift;
	}
	public void setLift(boolean isLift) {
		this.isLift = isLift;
	}
	public Set<Long> getFlats() {
		return flats;
	}
	public void setFlats(Set<Long> flats) {
		this.flats = flats;
	}
	public int getNumberOfFlats() {
		return numberOfFlats;
	}
	public void setNumberOfFlats(int numberOfFlats) {
		this.numberOfFlats = numberOfFlats;
	}

	
}
