package com.capgemini.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.domain.BuildingEntity;
import com.capgemini.types.BuildingTO;

public class BuildingMapper {

	public static BuildingTO toBuildingTO(BuildingEntity buildingEntity) {

		if (buildingEntity == null) {
			return null;
		}

		BuildingTO buildingTO = new BuildingTO();

		buildingTO.setId(buildingEntity.getId());
		buildingTO.setDescription(buildingEntity.getDescription());
		buildingTO.setAddress(buildingEntity.getAddress());
		buildingTO.setLift(buildingEntity.isLift());
		buildingTO.setNumberOfFlats(buildingEntity.getNumberOfFlats());
		buildingTO.setNumberOfFlors(buildingEntity.getNumberOfFlors());

		buildingTO.setFlats(buildingEntity.getFlats().stream()
								.map(f -> f.getId())
								.collect(Collectors.toSet()));

		return buildingTO;
	}

	public static BuildingEntity toBuildingEntity(BuildingTO buildingTO) {
		if (buildingTO == null) {
			return null;
		}
		BuildingEntity buildingEntity = new BuildingEntity();

		buildingEntity.setId(buildingTO.getId());
		buildingEntity.setDescription(buildingTO.getDescription());
		buildingEntity.setAddress(buildingTO.getAddress());
		buildingEntity.setLift(buildingTO.isLift());
		buildingEntity.setNumberOfFlats(buildingTO.getNumberOfFlats());
		buildingEntity.setNumberOfFlors(buildingTO.getNumberOfFlors());

		return buildingEntity;
	}
	public static List<BuildingTO> toBuildingTOList(List<BuildingEntity> buildings) {
		return buildings.stream().map(BuildingMapper::toBuildingTO).collect(Collectors.toList());
	}
	
	public static List<BuildingEntity> toBuildingEntityList(List<BuildingTO> buildings){
		return buildings.stream().map(BuildingMapper::toBuildingEntity).collect(Collectors.toList());
	}
}
