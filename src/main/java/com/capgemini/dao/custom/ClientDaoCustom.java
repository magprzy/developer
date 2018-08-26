package com.capgemini.dao.custom;

import java.util.List;

import com.capgemini.domain.ClientEntity;

public interface ClientDaoCustom {

	List<ClientEntity> findAllClientsWithAtLeastTwoFlats();
}
