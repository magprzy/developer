package com.capgemini.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
import com.capgemini.dao.ClientDao;
import com.capgemini.dao.FlatDao;
import com.capgemini.domain.BuildingEntity;
import com.capgemini.domain.ClientEntity;
import com.capgemini.domain.FlatEntity;
import com.capgemini.enums.FlatStatus;
import com.capgemini.types.BuildingTO;
import com.capgemini.types.FlatTO;

import model.Address;
import model.FlatSearchCriteria;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class FlatServiceTest {

	
	@Autowired
	private BuildingService buildingService;

	@Autowired
	private FlatService flatService;

	@Autowired
	private BuildingDao buildingDao;

	@Autowired
	private FlatDao flatDao;

	@Autowired
	private ClientDao clientDao;

	@Test
	@Transactional
	public void shouldAddFlat() {
		// given

		FlatTO flat = new FlatTO(80L, 4L, 1L, 3L, new Address("Poznan", "Kolorowa", "6/56"), FlatStatus.FREE, 500000L,
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
	public void shouldUpdateFlat() {
		// given
		FlatTO addedFlat = addTestFlat();

		// when
		FlatTO selectedFlat = flatService.findFlatById(addedFlat.getId());
		selectedFlat.setFlor(7L);
		flatService.updateFlat(selectedFlat);

		// then
		assertEquals(new Long(7), flatService.findFlatById(addedFlat.getId()).getFlor());
	}

	/*@Test
	@Transactional
	public void shouldAddFlatToBuilding() {

		// given
		FlatTO flat = addTestFlat();
		BuildingTO building = addTestBuilding();

		flatService.addFlatToBuilding(building.getId(), flat.getId());

		// assertNotNull(buildingService.findBuildingById(building.getId()).getFlats());
		assertEquals(1, buildingDao.findOne(building.getId()).getFlats().size());
		// assertEquals(1,
		// buildingService.findBuildingById(building.getId()).getFlats().size());
		assertTrue(buildingService.findBuildingById(building.getId()).getFlats().contains(flat.getId()));
	}*/

	@Test
	@Transactional
	public void shouldFindAllFreeFlats() {
		addTestFlats();

		List<FlatEntity> flats = flatDao.findFreeFlats();

		assertEquals(4, flats.size());
	}

	@Test
	@Transactional
	public void shouldFindAllFreeFlatsWithSizeFromTo() {
		addTestFlats();
		FlatSearchCriteria fsq = new FlatSearchCriteria(50L, 100L, 3L, 6L, 1L, 5L);
		// FlatSearchCriteria fsq = new FlatSearchCriteria(null, null, 3L, 6L,
		// null, null);
		List<FlatEntity> flats = flatDao.findFreeFlats(fsq);

		assertEquals(3, flats.size());
	}

	@Test
	@Transactional
	public void shouldCountAveragePriceOfFlatInBuilding() {
		addTestFlatsEntity();
		Long id = buildingDao.findAll().iterator().next().getId();
		Double result = flatDao.countAveragePriceOfFlatInBuilding(id);

		assertEquals(new Double(550000), result);
	}

	@Test
	@Transactional
	public void shouldCountSumOfFlatsByClient() {
		addTestFlatsEntity();
		Long id = clientDao.findAll().iterator().next().getId();
		Long result = flatDao.findPriceAllFlatsByCustomer(id);
		
		assertEquals(new Long(1400000), result);
	}
	
	@Test
	@Transactional
	public void shouldCountFlatsInBuldingWithStatus(){
		addTestFlatsEntity();
		Long id = buildingDao.findAll().iterator().next().getId();
		
		Long result = flatDao.countFlatWithStatusInBuilding(FlatStatus.BOOKED, id);
		Long result2 = flatDao.countFlatWithStatusInBuilding(FlatStatus.SOLD, id);
		assertEquals(new Long(1), result);
		assertEquals(new Long(3), result2);
		}
	
	@Test
	@Transactional
	public void shouldFindAllClientsWithMinTwoFlats(){
		
		addTestFlatsEntity();
		List<ClientEntity> result = clientDao.findAllClientsWithMinTwoFlats();
		
		assertEquals(1, result.size());
	}
	
	// insert test data
	
	public FlatTO addTestFlat() {
		BuildingTO building = new BuildingTO("Apartment for family", new Address("Poznan", "Kolorowa", "6"), 10, true,
				null, 20);
		BuildingTO addedBuilding = buildingService.addBuilding(building);

		FlatTO flat = new FlatTO(80L, 4L, 1L, 3L, new Address("Poznan", "Kolorowa", "6/56"), FlatStatus.FREE, 500000L,
				addedBuilding.getId(), null);
		FlatTO addedFflat = flatService.addFlat(flat);
		return addedFflat;
	}

	public void addTestFlats() {
		FlatTO freeFlat = new FlatTO(80L, 4L, 1L, 3L, new Address("Poznan", "Kolorowa", "6/56"), FlatStatus.FREE,
				500000L, 3L, null);
		flatService.addFlat(freeFlat);
		flatService.addFlat(freeFlat);
		flatService.addFlat(freeFlat);
		FlatTO freeFlat2 = new FlatTO(55L, 2L, 1L, 3L, new Address("Poznan", "Kolorowa", "6/56"), FlatStatus.FREE,
				450000L, 3L, null);
		flatService.addFlat(freeFlat2);

		// Set<Long> clients = new HashSet<>();
		FlatTO soldFlat = new FlatTO(80L, 4L, 1L, 7L, new Address("Poznan", "Kolorowa", "6/56"), FlatStatus.SOLD,
				1800000L, 3L, null);
		flatService.addFlat(soldFlat);
		flatService.addFlat(soldFlat);
		// flatService.addFlat(soldFlat);

	}

	public void addTestFlatsEntity() {
		
		BuildingEntity building = new BuildingEntity();
		BuildingEntity addedBuilding = buildingDao.save(building);

		ClientEntity client = new ClientEntity("Jan", "Kowalski");
		ClientEntity addedClient = clientDao.save(client);
		ClientEntity client2 = new ClientEntity("Jan", "Nowak");
		ClientEntity addedClient2 = clientDao.save(client2);

		Set<ClientEntity> clients = new HashSet<>();
		clients.add(addedClient);
		
		Set<ClientEntity> clients2 = new HashSet<>();
		clients2.add(addedClient2);

		FlatEntity freeFlat1 = new FlatEntity(80L, 4L, 1L, 3L, new Address("Poznan", "Kolorowa", "6/56"),
				FlatStatus.FREE, 500000L, addedBuilding, null);
		FlatEntity freeFlat2 = new FlatEntity(80L, 4L, 1L, 3L, new Address("Poznan", "Kolorowa", "6/56"),
				FlatStatus.FREE, 500000L, addedBuilding, null);
		FlatEntity freeFlat3 = new FlatEntity(80L, 4L, 1L, 3L, new Address("Poznan", "Kolorowa", "6/56"),
				FlatStatus.SOLD, 500000L, addedBuilding, clients2);
		flatDao.save(freeFlat1);
		flatDao.save(freeFlat2);
		flatDao.save(freeFlat3);

		
		FlatEntity bookedFlat1 = new FlatEntity(55L, 2L, 1L, 3L, new Address("Poznan", "Kolorowa", "6/56"),
				FlatStatus.BOOKED, 400000L, addedBuilding, clients);
		flatDao.save(bookedFlat1);

		FlatEntity soldFlat1 = new FlatEntity(80L, 4L, 1L, 7L, new Address("Poznan", "Kolorowa", "6/56"),
				FlatStatus.SOLD, 480000L, addedBuilding, clients);

		FlatEntity soldFlat2 = new FlatEntity(80L, 4L, 1L, 7L, new Address("Poznan", "Kolorowa", "6/56"),
				FlatStatus.SOLD, 920000L, addedBuilding, clients);
		
		/*FlatEntity soldFlat3 = new FlatEntity(80L, 4L, 1L, 7L, new Address("Poznan", "Kolorowa", "6/56"),
				FlatStatus.SOLD, 920000L, addedBuilding, clients);*/

		flatDao.save(soldFlat1);
		flatDao.save(soldFlat2);
		//flatDao.save(soldFlat3);

	}

	public BuildingTO addTestBuilding() {
		BuildingTO building = new BuildingTO("Apartment for family", new Address("Poznan", "Kolorowa", "6"), 10, true,
				null, 20);
		BuildingTO addedBuilding = buildingService.addBuilding(building);
		return addedBuilding;
	}
}
