package com.capgemini.listeners;

import java.util.Date;

public interface Auditable {

	void setDateCreated(Date date);
	
	Date getDateCreated();
	
	void setDateLastUpdated(Date date);
	
	Date getDateLastUpdated();
}
