package model;

import javax.persistence.Embeddable;

@Embeddable
public class ContactDetails {
	
	private String phoneNumber;
	private String email;
	
	public ContactDetails(){
		
	}
	public ContactDetails(String phoneNumber, String email) {
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
