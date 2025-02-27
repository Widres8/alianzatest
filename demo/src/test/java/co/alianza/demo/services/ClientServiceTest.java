package co.alianza.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import co.alianza.demo.common.dtos.ClientDto;
import co.alianza.demo.common.utils.exceptions.EntityAlreadyFoundException;
import co.alianza.demo.common.utils.exceptions.EntityNotFoundException;
import co.alianza.demo.services.interfaces.IClientService;

class ClientServiceTest {

  @Mock
  private IClientService clientService;

  @Mock
  private List<ClientDto> clients;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    clientService = new ClientService();
  }

  @Test
  void testGetAll() {
    List<ClientDto> result = clientService.getAll(null);
    assertNotNull(result);
    assertEquals(3, result.size());
  }

  @Test
  void testGetById_Success() {
    var client = clientService.getById(1L);
    assertNotNull(client);
    assertEquals(1L, client.getId());
  }

  @Test
  void testGetById_NotFound() {
    assertThrows(EntityNotFoundException.class, () -> clientService.getById(99L));
  }

  @Test
  void testCreate_Success() {
    var newClient = ClientDto.builder()
      .username("newuser")
      .name("New User")
      .email("newuser@example.com")
      .phone("321987654322")
      .startDate(new Date())
      .build();

    var createdClient = clientService.create(newClient);
    assertNotNull(createdClient);
    assertEquals("newuser", createdClient.getUsername());
  }

  @Test
  void testCreate_AlreadyExists() {
    var existingClient = ClientDto.builder()
      .username("jgutierrez")
      .name("Juliana Rodriguez")
      .email("juliana@example.com")
      .phone("321987654321")
      .startDate(new Date())
      .build();
    assertThrows(EntityAlreadyFoundException.class, () -> clientService.create(existingClient));
  }

  @Test
  void testUpdate_Success() {
    var updatedClient = ClientDto.builder().id(1L).username("jgutierrez").name("Juliana Rodriguez Updated").email("juliana_updated@example.com").phone("321987654321")
      .startDate(new Date()).build();
    boolean result = clientService.update(updatedClient);
    assertTrue(result);
  }

  @Test
  void testUpdate_NotFound() {
    var nonExistingClient = ClientDto.builder()
      .id(99L).username("nonexistent")
      .name("Non Existent")
      .email("nonexistent@example.com")
      .phone("321987654322")
      .startDate(new Date()).build();
    assertThrows(EntityNotFoundException.class, () -> clientService.update(nonExistingClient));
  }

  @Test
  void testDelete_Success() {
    var result = clientService.delete(1L);
    assertTrue(result);
  }

  @Test
  void testDelete_NotFound() {
    var result = clientService.delete(99L);
    assertFalse(result);
  }
}
