package com.capgemini.dao;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.dao.custom.ClientDaoCustom;
import com.capgemini.domain.ClientEntity;

public interface ClientDao extends CrudRepository<ClientEntity, Long>, ClientDaoCustom {
	
}
