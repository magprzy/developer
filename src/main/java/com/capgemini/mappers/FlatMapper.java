package com.capgemini.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.domain.FlatEntity;
import com.capgemini.types.FlatTO;

public class FlatMapper {

	public static FlatTO toFlatTO(FlatEntity flatEntity) {
		if (flatEntity == null) {
			return null;
		}
		FlatTO flatTO = new FlatTO();

		flatTO.setId(flatEntity.getId());
		flatTO.setSize(flatEntity.getSize());
		flatTO.setNumberOfRooms(flatEntity.getNumberOfRooms());
		flatTO.setNumberOfBalconies(flatEntity.getNumberOfBalconies());
		flatTO.setFlor(flatEntity.getFlor());
		flatTO.setAddress(flatEntity.getAddress());
		flatTO.setStatus(flatEntity.getStatus());
		flatTO.setPrice(flatEntity.getPrice());
		flatTO.setBuildingId(flatEntity.getBuilding() == null ? null : flatEntity.getBuilding().getId());

		flatTO.setClients(flatEntity.getClients().stream().map(c -> c.getId()).collect(Collectors.toSet()));

		return flatTO;
	}

	public static FlatEntity toFlatEntity(FlatTO flatTO) {
		if (flatTO == null) {
			return null;
		}
		FlatEntity flatEntity = new FlatEntity();

		flatEntity.setId(flatTO.getId());
		flatEntity.setSize(flatTO.getSize());
		flatEntity.setNumberOfRooms(flatTO.getNumberOfRooms());
		flatEntity.setNumberOfBalconies(flatTO.getNumberOfBalconies());
		flatEntity.setFlor(flatTO.getFlor());
		flatEntity.setAddress(flatTO.getAddress());
		flatEntity.setStatus(flatTO.getStatus());
		flatEntity.setPrice(flatTO.getPrice());

		return flatEntity;
	}

	public static List<FlatTO> toFlatTOList(List<FlatEntity> flats) {
		return flats.stream().map(FlatMapper::toFlatTO).collect(Collectors.toList());
	}

	public static List<FlatEntity> toFlatEntityList(List<FlatTO> flats) {
		return flats.stream().map(FlatMapper::toFlatEntity).collect(Collectors.toList());
	}
}
