package com.capgemini.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.capgemini.enums.FlatStatus;
import com.capgemini.listeners.Auditable;
import com.capgemini.listeners.PreListeners;

import model.Address;
@Entity
@Table (name = "Flat")
@EntityListeners (PreListeners.class)
public class FlatEntity implements Auditable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	

	private Long size;
	
	
	private int numberOfRooms;
	
	
	private int numberOfBalconies;
	
	
	private int flor;
	
	
	@Embedded
	private Address address;
	
	
	@Enumerated(EnumType.STRING)
	private FlatStatus status;
	

	private Long price;
	
	@ManyToOne
	private BuildingEntity building;
	
	@ManyToMany
	private Set<ClientEntity> clients = new HashSet<>();
	
	
	private Date dateCreated;
	
	private Date dateLastUpdated;
	
	@Version
	private Long version;
	
	
	public FlatEntity() {
	
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	public int getNumberOfRooms() {
		return numberOfRooms;
	}
	public void setNumberOfRooms(int numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}
	public int getNumberOfBalconies() {
		return numberOfBalconies;
	}
	public void setNumberOfBalconies(int numnerOfBalconies) {
		this.numberOfBalconies = numnerOfBalconies;
	}
	public int getFlor() {
		return flor;
	}
	public void setFlor(int flor) {
		this.flor = flor;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public FlatStatus getStatus() {
		return status;
	}
	public void setStatus(FlatStatus status) {
		this.status = status;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public BuildingEntity getBuilding() {
		return building;
	}
	public void setBuilding(BuildingEntity building) {
		this.building = building;
	}
	public Set<ClientEntity> getClients() {
		return clients;
	}
	public void setClients(Set<ClientEntity> clients) {
		this.clients = clients;
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
