package com.capgemini.dao.custom;

import java.util.List;

import com.capgemini.domain.FlatEntity;
import com.capgemini.enums.FlatStatus;

import model.FlatSearchCriteria;

public interface FlatDaoCustom {
	List<FlatEntity> findFreeFlats();

	Long findPriceAllFlatsByCustomer(Long client);

	List<FlatEntity> findFreeFlats(FlatSearchCriteria criteria);

	Double countAveragePriceOfFlatInBuilding(Long buildingID);

	Long countFlatWithStatusInBuilding(FlatStatus status, Long buildingId);

	List<FlatEntity> findFlatsForDisabled();
}
