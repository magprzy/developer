package com.capgemini.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.BuildingDao;
import com.capgemini.dao.ClientDao;
import com.capgemini.dao.FlatDao;
import com.capgemini.domain.FlatEntity;
import com.capgemini.enums.FlatStatus;
import com.capgemini.types.BuildingTO;
import com.capgemini.types.FlatTO;

import model.Address;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class FlatServiceTest {

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
	public void shouldAddFlat() {
		// given
		
		FlatTO flat = new FlatTO(80L, 4, 1, 3, new Address("Poznan", "Kolorowa", "6/56"), FlatStatus.FREE, 500000L,
				null, null);
		FlatTO addedFlat = flatService.addFlat(flat);
		
		// when
		
		// then
		assertNotNull(flatService.findAllFlats());
		assertEquals("Kolorowa", addedFlat.getAddress().getStreet());
		assertEquals(new Long(80), addedFlat.getSize());
	}

	@Test
	@Transactional
	public void shouldRemoveFlat() {
		// given
		FlatTO addedFlat = addTestFlat();
		
		// when
		flatService.removeFlat(addedFlat.getId());
		
		// then
		assertTrue(flatService.findAllFlats().isEmpty());
	}
	
	@Test
	@Transactional
	public void shouldUpdateFlat(){
		//given
		FlatTO addedFlat = addTestFlat();
		
		//when
		FlatTO selectedFlat = flatService.findFlatById(addedFlat.getId());
		selectedFlat.setFlor(7);
		flatService.updateFlat(selectedFlat);
		
		//then
		assertEquals(7, flatService.findFlatById(addedFlat.getId()).getFlor());
	}
	
	@Test
	@Transactional
	public void shouldAddFlatToBuilding(){
		
		//given
		FlatTO flat = addTestFlat();
		BuildingTO building = addTestBuilding();
		
		flatService.addFlatToBuilding(building.getId(), flat.getId());
		
		//assertNotNull(buildingService.findBuildingById(building.getId()).getFlats());
		assertEquals(1, buildingDao.findOne(building.getId()).getFlats().size());
		//assertEquals(1, buildingService.findBuildingById(building.getId()).getFlats().size());
		assertTrue(buildingService.findBuildingById(building.getId()).getFlats().contains(flat.getId()));
	}
	
	
	
	@Test
	@Transactional
	public void shouldFindAllFreeFlats(){
		addTestFlats();
		
		List<FlatEntity> flats = flatDao.findFreeFlats();
		
		assertEquals(4, flats.size());
	}
	

	public FlatTO addTestFlat() {
		BuildingTO building = new BuildingTO("Apartment for family" ,  new Address("Poznan", "Kolorowa", "6"), 10, true, null, 20);
		BuildingTO addedBuilding = buildingService.addBuilding(building);
		
		FlatTO flat = new FlatTO(80L, 4, 1, 3, new Address("Poznan", "Kolorowa", "6/56"), FlatStatus.FREE, 500000L,
				addedBuilding.getId(), null);
		FlatTO addedFflat = flatService.addFlat(flat);
		return addedFflat;
	}
	
	
	public void addTestFlats(){
		FlatTO freeFlat = new FlatTO(80L, 4, 1, 3, new Address("Poznan", "Kolorowa", "6/56"), FlatStatus.FREE, 500000L,
				null, null);
	flatService.addFlat(freeFlat);
	flatService.addFlat(freeFlat);
	flatService.addFlat(freeFlat);
	flatService.addFlat(freeFlat);
	
	FlatTO soldFlat = new FlatTO(80L, 4, 1, 7, new Address("Poznan", "Kolorowa", "6/56"), FlatStatus.SOLD, 500000L,
			null, null);
	flatService.addFlat(soldFlat);
	flatService.addFlat(soldFlat);
	flatService.addFlat(soldFlat);
	
	
		
	}
	
public BuildingTO addTestBuilding(){
		BuildingTO building = new BuildingTO("Apartment for family" ,  new Address("Poznan", "Kolorowa", "6"), 10, true, null, 20);
		BuildingTO addedBuilding = buildingService.addBuilding(building);
		return addedBuilding;
	}
}
