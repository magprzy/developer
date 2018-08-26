package com.capgemini.dao;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.dao.custom.FlatDaoCustom;
import com.capgemini.domain.FlatEntity;

public interface FlatDao extends CrudRepository<FlatEntity, Long>, FlatDaoCustom{

	
}
