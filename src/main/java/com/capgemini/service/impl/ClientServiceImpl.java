package com.capgemini.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.ClientDao;
import com.capgemini.dao.FlatDao;
import com.capgemini.domain.ClientEntity;
import com.capgemini.domain.FlatEntity;
import com.capgemini.enums.FlatStatus;
import com.capgemini.mappers.ClientMapper;
import com.capgemini.service.ClientService;
import com.capgemini.types.ClientTO;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {
	@Autowired
	private ClientDao clientDao;

	@Autowired
	private FlatDao flatDao;

	@Override
	public ClientTO addClient(ClientTO client) {
		ClientEntity clientEntity = clientDao.save(ClientMapper.toClientEntity(client));
		return ClientMapper.toClientTO(clientEntity);
	}

	@Override
	public void removeClient(Long clientId) {
		clientDao.delete(clientId);
	}

	@Override
	public ClientTO findClientById(Long clientId) {
		ClientEntity clientEntity = clientDao.findOne(clientId);
		return ClientMapper.toClientTO(clientEntity);
	}

	@Override
	public void updateClient(ClientTO client) {
		ClientEntity clientEntity = clientDao.findOne(client.getId());
		clientEntity.setFirstName(client.getFirstName());
		clientEntity.setLastName(client.getLastName());
		clientEntity.setAddress(client.getAddress());
		clientEntity.setContact(client.getContact());

		clientDao.save(clientEntity);
	}

	@Override
	public List<ClientTO> findAllClients() {

		return ClientMapper.toClientTOList(Lists.newArrayList(clientDao.findAll()));

	}

	@Override
	public void buyFlat(Long clientId, Long flatId) throws IllegalAccessException {
		ClientEntity clientEntity = clientDao.findOne(clientId);
		FlatEntity flatEntity = flatDao.findOne(flatId);
		
		//check if flat is booked by other client
		if (clientEntity.getFlats().contains(flatEntity) == false && (flatEntity.getStatus().equals(FlatStatus.BOOKED))){
			throw new IllegalAccessException("This flat is booked by other clint");
		
		}
		else
		{
			flatEntity.setStatus(FlatStatus.SOLD);

			clientEntity.getFlats().add(flatEntity);
			clientDao.save(clientEntity);

			flatEntity.getClients().add(clientEntity);
			flatDao.save(flatEntity);
		}

	}

	@Override
	public void bookFlat(Long clientId, Long flatId) throws IllegalAccessException {
		ClientEntity clientEntity = clientDao.findOne(clientId);
		FlatEntity flatEntity = flatDao.findOne(flatId);

		if (flatEntity.getStatus().equals(FlatStatus.SOLD) == false) {
			if (clientEntity.getFlats().stream().filter(s -> s.getStatus().equals(FlatStatus.BOOKED))
					.collect(Collectors.toList()).size() < 3) {
				flatEntity.setStatus(FlatStatus.BOOKED);

				clientEntity.getFlats().add(flatEntity);
				clientDao.save(clientEntity);

				flatEntity.getClients().add(clientEntity);
				flatDao.save(flatEntity);
			} else {
				throw new IllegalAccessException("Client has already has 3 reservation");
			}
		}
		else {
			throw new IllegalAccessException("This flat is sold");
					
		}
	}

}
