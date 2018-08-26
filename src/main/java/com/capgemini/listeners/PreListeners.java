package com.capgemini.listeners;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class PreListeners {

	@PrePersist
	void onPrePersist(Auditable auditable){
		auditable.setDateCreated(new Date());
	}
	
	
	@PreUpdate
	void onPreUpdate(Auditable auditable){
		auditable.setDateLastUpdated(new Date());
	}
}
