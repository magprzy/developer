package com.capgemini.dao.custom;

import java.util.List;

import com.capgemini.domain.FlatEntity;

import model.FlatSearchCriteria;

public interface FlatDaoCustom {
	List<FlatEntity> findFreeFlats();
	
	Long findPriceAllFlatsByCustomer(Long customerId);

	List<FlatEntity> findFreeFlats(FlatSearchCriteria criteria);
}
