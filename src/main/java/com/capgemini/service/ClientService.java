package com.capgemini.service;

import java.util.List;

import com.capgemini.types.ClientTO;

public interface ClientService {

	ClientTO addClient(ClientTO client);

	void removeClient(Long clientId);

	ClientTO findClientById(Long clientId);

	void updateClient(ClientTO client);
	
	List<ClientTO> findAllClients();
	
	void buyFlat(Long clientId, Long flatId) throws IllegalAccessException;
	
	void bookFlat(Long clientId, Long flatId) throws IllegalAccessException;

}
