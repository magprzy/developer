package com.capgemini.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.ClientDao;
import com.capgemini.domain.ClientEntity;
import com.capgemini.enums.FlatStatus;
import com.capgemini.types.BuildingTO;
import com.capgemini.types.ClientTO;
import com.capgemini.types.FlatTO;


import model.Address;
import model.ContactDetails;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ClientServiceTest {

	@Autowired
	private ClientService clientService;

	@Autowired
	private BuildingService buildingService;

	@Autowired
	private FlatService flatService;
	@Autowired
	private ClientDao clientDao;

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
	public void shouldAddSoldFlatToClient() {
		// given
		ClientTO client = addTestClient();
		FlatTO flat = addTestFlat();

		// when
		clientService.buyFlat(client.getId(), flat.getId());

		// then
		assertNotNull(clientService.findClientById(client.getId()).getFlats());
		assertEquals(1, clientService.findClientById(client.getId()).getFlats().size());
		assertTrue(clientService.findClientById(client.getId()).getFlats().contains(flat.getId()));
	}

	@Test
	@Transactional
	
	public void shouldAddClientToSoldFlat() {
		// given
		ClientTO client = addTestClient();
		FlatTO flat = addTestFlat();

		// when
		clientService.buyFlat(client.getId(), flat.getId());
	}

	public ClientTO addTestClient() {
		ClientTO client = new ClientTO("Janusz", "Borowik", new Address("Poznan", "Kolorowa", "6/56"),
				new ContactDetails("987654321", "janusz@borowik.pl"));
		ClientTO addedClient = clientService.addClient(client);
		return addedClient;
	}

	public BuildingTO addTestBuilding() {
		BuildingTO building = new BuildingTO("Apartment for family", new Address("Poznan", "Kolorowa", "6"), 10, true,
				null, 20);
		BuildingTO addedBuilding = buildingService.addBuilding(building);
		return addedBuilding;
	}

	public FlatTO addTestFlat() {
		FlatTO flat = new FlatTO(80L, 4, 1, 3, new Address("Poznan", "Kolorowa", "6/56"), FlatStatus.FREE, 500000L,
				(long) 1, null);
		FlatTO addedFlat = flatService.addFlat(flat);
		return addedFlat;
	}

}
