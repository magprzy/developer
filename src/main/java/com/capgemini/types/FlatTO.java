package com.capgemini.types;

import java.util.Set;

import com.capgemini.enums.FlatStatus;

import model.Address;

public class FlatTO {
	
	
	private Long id;
	private Long size;
	private Long numberOfRooms;
	private Long numberOfBalconies;
	private Long flor;
	private Address address;
	private FlatStatus status;
	private Long price;
	private Long buildingId;
	private Set<Long> clients;
	
	public FlatTO(){
		
	}
	public FlatTO(Long size, Long numberOfRooms, Long numberOfBalconies, Long flor, Address address, FlatStatus status,
			Long price) {
		super();
		this.size = size;
		this.numberOfRooms = numberOfRooms;
		this.numberOfBalconies = numberOfBalconies;
		this.flor = flor;
		this.address = address;
		this.status = status;
		this.price = price;
	
	}
	public FlatTO(Long size, Long numberOfRooms, Long numberOfBalconies, Long flor, Address address, FlatStatus status,
			Long price, Long buildingId, Set<Long> clients) {
		super();
		this.size = size;
		this.numberOfRooms = numberOfRooms;
		this.numberOfBalconies = numberOfBalconies;
		this.flor = flor;
		this.address = address;
		this.status = status;
		this.price = price;
		this.buildingId = buildingId;
		this.clients = clients;
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
	public Long getNumberOfRooms() {
		return numberOfRooms;
	}
	public void setNumberOfRooms(Long numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}
	public Long getNumberOfBalconies() {
		return numberOfBalconies;
	}
	public void setNumberOfBalconies(Long numnerOfBalconies) {
		this.numberOfBalconies = numnerOfBalconies;
	}
	public Long getFlor() {
		return flor;
	}
	public void setFlor(Long flor) {
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
	public Long getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}
	public Set<Long> getClients() {
		return clients;
	}
	public void setClients(Set<Long> clients) {
		this.clients = clients;
	}
	
}
