package com.capgemini.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.capgemini.dao.BuildingDao;

import com.capgemini.domain.BuildingEntity;
import com.capgemini.mappers.BuildingMapper;
import com.capgemini.service.BuildingService;
import com.capgemini.types.BuildingTO;

@Service
@Transactional
public class BuildingServiceImpl implements BuildingService {
	@Autowired
	private BuildingDao buildingDao;
	
	@Override
	public BuildingTO addBuilding(BuildingTO building) {
		BuildingEntity buildingEntity = buildingDao.save(BuildingMapper.toBuildingEntity(building));
		return BuildingMapper.toBuildingTO(buildingEntity);
	}

	@Override
	public void removeBuilding(Long buildingId) {
		buildingDao.delete(buildingId);	
	}

	@Override
	public BuildingTO findBuildingById(Long buildingId) {
		BuildingEntity buildingEntity = buildingDao.findOne(buildingId);
		return BuildingMapper.toBuildingTO(buildingEntity);
	}

	@Override
	public void updateBuilding(BuildingTO building) {
		BuildingEntity buildingEntity = buildingDao.findOne(building.getId());
		buildingEntity.setAddress(building.getAddress());
		buildingEntity.setDescription(building.getDescription());
		buildingEntity.setLift(building.isLift());
		buildingEntity.setNumberOfFlats(building.getNumberOfFlats());
		buildingEntity.setNumberOfFlors(building.getNumberOfFlors());
		
		buildingDao.save(buildingEntity);	
	}

	@Override
	public List<BuildingTO> findAllBuildings() {
		
		return BuildingMapper.toBuildingTOList(Lists.newArrayList(buildingDao.findAll()));
	}

	

}
