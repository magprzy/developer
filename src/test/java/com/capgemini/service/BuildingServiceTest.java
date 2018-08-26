package com.capgemini.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

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
	
}
