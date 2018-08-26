package com.capgemini.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.domain.ClientEntity;
import com.capgemini.types.ClientTO;

public class ClientMapper {

	public static ClientTO toClientTO(ClientEntity clientEntity) {
		if (clientEntity == null) {
			return null;
		}
		ClientTO clientTO = new ClientTO();

		clientTO.setId(clientEntity.getId());
		clientTO.setFirstName(clientEntity.getFirstName());
		clientTO.setLastName(clientEntity.getLastName());
		clientTO.setAddress(clientEntity.getAddress());
		clientTO.setContact(clientEntity.getContact());

		clientTO.setFlats(clientEntity.getFlats().stream().map(f -> f.getId()).collect(Collectors.toSet()));
		return clientTO;

	}

	public static ClientEntity toClientEntity(ClientTO clientTO) {
		if (clientTO == null) {
			return null;
		}
		ClientEntity clientEntity = new ClientEntity();

		clientEntity.setId(clientTO.getId());
		clientEntity.setFirstName(clientTO.getFirstName());
		clientEntity.setLastName(clientTO.getLastName());
		clientEntity.setAddress(clientTO.getAddress());
		clientEntity.setContact(clientTO.getContact());

		return clientEntity;
	}

	public static List<ClientTO> toClientTOList(List<ClientEntity> clients) {
		return clients.stream().map(ClientMapper::toClientTO).collect(Collectors.toList());
	}
	
	public static List<ClientEntity> toClientEntityList(List<ClientTO> clients){
		return clients.stream().map(ClientMapper::toClientEntity).collect(Collectors.toList());
	}
}
