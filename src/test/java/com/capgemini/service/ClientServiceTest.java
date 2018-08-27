package com.capgemini.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;

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
import com.capgemini.types.ClientTO;

import model.Address;
import model.ContactDetails;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ClientServiceTest {

	@Autowired
	private ClientService clientService;

	@Autowired
	private FlatService flatService;
	@Autowired
	private ClientDao clientDao;

	@Autowired
	private FlatDao flatDao;

	@Autowired
	private BuildingDao buildingDao;

	@Test
	@Transactional
	public void shouldAddClient() {
		// given
		ClientTO clientTO = new ClientTO("Janusz", "Borowik", new Address("Poznan", "Kolorowa", "6/56"),
				new ContactDetails("987654321", "janusz@borowik.pl"));
		
		// when
		ClientTO addedClient = clientService.addClient(clientTO);
		
		// then
		assertNotNull(clientService.findAllClients());
		assertEquals("Janusz", clientDao.findOne(addedClient.getId()).getFirstName());
		assertEquals("Borowik", clientDao.findOne(addedClient.getId()).getLastName());
	}

	@Test
	@Transactional
	public void shouldRemoveClient() {
		// given
		ClientTO addedClient = addTestClient();

		// when
		clientService.removeClient(addedClient.getId());

		// then
		assertTrue(clientService.findAllClients().isEmpty());
	}

	@Test
	@Transactional
	public void shouldUpdateClient() {
		// given
		ClientTO addedClient = addTestClient();

		// when
		ClientTO selectedClient = clientService.findClientById(addedClient.getId());
		selectedClient.setFirstName("Grazyna");
		clientService.updateClient(selectedClient);

		// then
		assertEquals("Grazyna", clientService.findClientById(addedClient.getId()).getFirstName());
	}

	@Test
	@Transactional
	public void shouldAddSoldFlatToClient() throws IllegalAccessException {
		// given
		ClientTO client = addTestClient();
		FlatEntity flat = addTestFlat();

		// when
		clientService.buyFlat(client.getId(), flat.getId());

		// then
		assertNotNull(clientService.findClientById(client.getId()).getFlats());
		assertEquals(1, clientService.findClientById(client.getId()).getFlats().size());
		assertTrue(clientService.findClientById(client.getId()).getFlats().contains(flat.getId()));
	}

	@Test
	@Transactional

	public void shouldAddClientToSoldFlat() throws IllegalAccessException {
		// given
		ClientTO client = addTestClient();
		ClientTO client2 = addTestClient();
		FlatEntity flat = addTestFlat();

		// when
		clientService.buyFlat(client.getId(), flat.getId());
		clientService.buyFlat(client2.getId(), flat.getId());

		// then
		// Set<Long> clients =
		// flatService.findFlatById(flat.getId()).getClients();
		assertNotNull(flatService.findFlatById(flat.getId()).getClients());
		assertEquals(2, flatService.findFlatById(flat.getId()).getClients().size());
		assertTrue(flatService.findFlatById(flat.getId()).getClients().contains(client.getId()));
		assertTrue(flatService.findFlatById(flat.getId()).getClients().contains(client2.getId()));
	}

	@Transactional
	@Test(expected = IllegalAccessException.class)
	public void shouldNotAdd4Reservation() throws IllegalAccessException {
		// given
		ClientTO client = addTestClient();
		FlatEntity flat1 = addTestFlat();
		FlatEntity flat2 = addTestFlat();
		FlatEntity flat3 = addTestFlat();

		clientService.bookFlat(client.getId(), flat1.getId());
		clientService.bookFlat(client.getId(), flat2.getId());
		clientService.bookFlat(client.getId(), flat3.getId());

		// when
		FlatEntity flat4 = addTestFlat();
		clientService.bookFlat(client.getId(), flat4.getId());

	}

	// insert testing data

	public ClientTO addTestClient() {
		ClientTO client = new ClientTO("Janusz", "Borowik", new Address("Poznan", "Kolorowa", "6/56"),
				new ContactDetails("987654321", "janusz@borowik.pl"));
		ClientTO addedClient = clientService.addClient(client);
		return addedClient;
	}

	public BuildingEntity addTestBuilding() {
		BuildingEntity building = new BuildingEntity(new HashSet<>(), "Beautiful building",
				new Address("Poznan", "Kolorowa", "6"), 5, true, 10);
		BuildingEntity addedBuilding = buildingDao.save(building);

		return addedBuilding;
	}

	public FlatEntity addTestFlat() {
		FlatEntity flat = new FlatEntity(80L, 4L, 1L, 0L, new Address("Warszawa", "Asnyka", "845/1"), FlatStatus.FREE,
				500000L, new BuildingEntity(), new HashSet<ClientEntity>());
		FlatEntity addedFlat = flatDao.save(flat);
		return addedFlat;
	}

}
