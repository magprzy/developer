package com.capgemini.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.BuildingDao;
import com.capgemini.dao.FlatDao;
import com.capgemini.domain.BuildingEntity;
import com.capgemini.domain.FlatEntity;
import com.capgemini.enums.FlatStatus;
import com.capgemini.mappers.FlatMapper;
import com.capgemini.service.FlatService;
import com.capgemini.types.FlatTO;
import com.google.common.collect.Lists;
@Service
@Transactional
public class FlatServiceImpl implements FlatService{

	@Autowired
	FlatDao flatDao;
	
	@Autowired
	BuildingDao buildingDao;
	
	@Override
	public FlatTO addFlat(FlatTO flat) {
		FlatEntity flatEntity = flatDao.save(FlatMapper.toFlatEntity(flat));
		return FlatMapper.toFlatTO(flatEntity);
	}

	@Override
	public void removeFlat(Long flatId) {
		flatDao.delete(flatId);
		
	}

	@Override
	public FlatTO findFlatById(Long flatId) {
		FlatEntity flatEntity = flatDao.findOne(flatId);
		return FlatMapper.toFlatTO(flatEntity);
	}
	
	@Override
	public List<FlatTO> findAllFlats() {
		
		return FlatMapper.toFlatTOList(Lists.newArrayList(flatDao.findAll()));
	}

	@Override
	public void updateFlat(FlatTO flat) {
		FlatEntity flatEntity = flatDao.findOne(flat.getId());
		flatEntity.setAddress(flat.getAddress());
		flatEntity.setFlor(flat.getFlor());
		flatEntity.setNumberOfBalconies(flat.getNumberOfBalconies());
		flatEntity.setNumberOfRooms(flat.getNumberOfRooms());
		flatEntity.setPrice(flat.getPrice());
		flatEntity.setSize(flat.getSize());
		
		flatDao.save(flatEntity);	
	}

	@Override
	public void addFlatToBuilding(Long flatId, Long buildingId) {
		FlatEntity flatEntity = flatDao.findOne(flatId);
		BuildingEntity buildingEntity = buildingDao.findOne(buildingId);
		
		buildingEntity.getFlats().add(flatEntity);
		buildingDao.save(buildingEntity);
		
		//flatEntity.setBuilding(buildingEntity);
		//flatDao.save(flatEntity);
	
		
	}
	
	@Override
	public List<FlatTO> findFlatsByBuilding(Long buildingId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FlatTO> findFlatsByBuildingAndStatus(Long buildingId, FlatStatus status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FlatTO> findFlatByClient(Long clientId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FlatTO> findAllFreeFlats() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
