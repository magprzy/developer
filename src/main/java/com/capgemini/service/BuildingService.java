package com.capgemini.service;

import java.util.List;

import com.capgemini.types.BuildingTO;

public interface BuildingService {
	BuildingTO addBuilding(BuildingTO building);
	
	void removeBuilding(Long buildingId);
	
	BuildingTO findBuildingById(Long buildingId);
	
	List<BuildingTO> findAllBuildings();

	void updateBuilding(BuildingTO building);
}
