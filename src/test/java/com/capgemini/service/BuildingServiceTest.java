package com.capgemini.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.BuildingDao;
import com.capgemini.dao.FlatDao;
import com.capgemini.domain.BuildingEntity;
import com.capgemini.domain.FlatEntity;
import com.capgemini.enums.FlatStatus;
import com.capgemini.types.BuildingTO;

import model.Address;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BuildingServiceTest {
	
	@Autowired
	private ClientService clientService;

	@Autowired
	private BuildingService buildingService;

	@Autowired
	private FlatService flatService;
	
	@Autowired
	private BuildingDao buildingDao;
	
	@Autowired
	private FlatDao flatDao;
	
	@Test
	@Transactional
	public void shouldAddBuilding() {
		// given
	
		BuildingTO building = new BuildingTO("Apartment for family", new Address("Poznan", "Kolorowa", "6"), 10, true,
				null, 22);

		// when
		BuildingTO addedBuilding = buildingService.addBuilding(building);
		// then
		assertNotNull(buildingService.findAllBuildings());
		assertEquals("Kolorowa", addedBuilding.getAddress().getStreet());
		assertEquals(22, addedBuilding.getNumberOfFlats());
}
	@Test
	@Transactional
	public void shouldFindBuildingHavingMaxFreeFlats() {
		addTestBuildingsAndFlats();
		
		BuildingEntity result = buildingDao.findBuildingWithMaxFreeFlats();
		assertEquals("Warszawa", result.getAddress().getCity());
	}
	
	
	@Test
	@Transactional
	public void shouldFindFlatsForDisabled(){
		addTestBuildingsAndFlats();
		
		List<FlatEntity> result = flatDao.findFlatsForDisabled();
		
		assertEquals(3, result.size());
	}
	
	public void addTestBuildingsAndFlats(){
		Set<FlatEntity> flats = new HashSet<>();
		Set<FlatEntity> flats2 = new HashSet<>();
		
		
		BuildingEntity building = new BuildingEntity(flats, "Beautiful building", new Address("Poznan", "Kolorowa", "6"), 5, true, 10);
		BuildingEntity addedBuilding = buildingDao.save(building);
		
		BuildingEntity building2 = new BuildingEntity(flats2, "Near the forest", new Address("Warszawa", "Asnyka", "845"),4,false,6);
		BuildingEntity addedBuilding2 = buildingDao.save(building2);
		
		FlatEntity freeFlat1 = new FlatEntity(80L, 4L, 1L, 0, new Address("Poznan", "Kolorowa", "6/56"),
				FlatStatus.FREE, 500000L, addedBuilding, null);
		FlatEntity freeFlat2 = new FlatEntity(80L, 4L, 1L, 3L, new Address("Poznan", "Kolorowa", "6/56"),
				FlatStatus.FREE, 500000L, addedBuilding, null);
		FlatEntity freeFlat3 = new FlatEntity(80L, 4L, 1L, 3L, new Address("Warszawa", "Asnyka", "845/3"),
				FlatStatus.FREE, 500000L, addedBuilding2, null);
		FlatEntity freeFlat4 = new FlatEntity(80L, 4L, 1L, 3L, new Address("Warszawa", "Asnyka", "845/2"),
				FlatStatus.FREE, 500000L, addedBuilding2, null);
		FlatEntity freeFlat5 = new FlatEntity(80L, 4L, 1L, 0, new Address("Warszawa", "Asnyka", "845/1"),
				FlatStatus.FREE, 500000L, addedBuilding2, null);
		flatDao.save(freeFlat1);
		flatDao.save(freeFlat2);
		flatDao.save(freeFlat3);
		flatDao.save(freeFlat4);
		flatDao.save(freeFlat5);
		
	}
}
