package com.capgemini.dao.custom;

import com.capgemini.domain.BuildingEntity;

public interface BuildingDaoCustom {

	BuildingEntity findBuildingWithMaxFreeFlats();

}
