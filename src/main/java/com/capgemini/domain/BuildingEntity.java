package com.capgemini.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import com.capgemini.listeners.Auditable;
import com.capgemini.listeners.PreListeners;

import model.Address;

@Entity
@Table (name = "Building")
@EntityListeners (PreListeners.class)
public class BuildingEntity implements Auditable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToMany (cascade = CascadeType.REMOVE, mappedBy = "building")
	private Set<FlatEntity> flats = new HashSet<>();
	
	private String description;
	
	@NotNull
	@Embedded
	private Address address;
	
	@NotNull
	private int numberOfFlors;
	
	@NotNull
	boolean isLift;
	
	@NotNull
	private int numberOfFlats;

	@NotNull
	private Date dateCreated;
	private Date dateLastUpdated;
	@Version
	private Long version;
	

	
	public BuildingEntity() {
		
	}
	
	
	public BuildingEntity(Set<FlatEntity> flats, String description, Address address, int numberOfFlors, boolean isLift,
			int numberOfFlats) {
		super();
		this.flats = flats;
		this.description = description;
		this.address = address;
		this.numberOfFlors = numberOfFlors;
		this.isLift = isLift;
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
	public int getNumberOfFlats() {
		return numberOfFlats;
	}
	public void setNumberOfFlats(int numberOfFlats) {
		this.numberOfFlats = numberOfFlats;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public Set<FlatEntity> getFlats() {
		return flats;
	}
	public void setFlats(Set<FlatEntity> flats) {
		this.flats = flats;
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
