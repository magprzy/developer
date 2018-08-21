package com.capgemini.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import model.Address;

@Entity
@Table (name = "Building")
public class BuildingEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Version
	private Long version;
	@OneToMany
	private Set<FlatEntity> flats = new HashSet<>();
	
	private String description;
	
	@Embedded
	private Address address;
	private int numberOfFlors;
	boolean isLift;
	private int numberOfFlats = flats.size();
	

	
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
	public int getNumberOfFlats() {
		return numberOfFlats;
	}
	public void setNumberOfFlats(int numberOfFlats) {
		this.numberOfFlats = numberOfFlats;
	}
	
	
}
