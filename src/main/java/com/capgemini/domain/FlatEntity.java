package com.capgemini.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.capgemini.enums.FlatStatus;

import model.Address;

@Entity
@Table (name = "Flat")
public class FlatEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Version
	private Long version;
	private Long size;
	private int numberOfRooms;
	private int numnerOfBalconies;
	private int flor;
	
	@Embedded
	private Address address;
	private FlatStatus status;
	private Long price;
	
	@ManyToOne
	private BuildingEntity building;
	
	@ManyToMany
	private Set<ClientEntity> clients = new HashSet<>();
	
	
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
	public int getNumnerOfBalconies() {
		return numnerOfBalconies;
	}
	public void setNumnerOfBalconies(int numnerOfBalconies) {
		this.numnerOfBalconies = numnerOfBalconies;
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
	
}
