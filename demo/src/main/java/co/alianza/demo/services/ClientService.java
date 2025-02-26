package co.alianza.demo.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import co.alianza.demo.common.dtos.ClientDto;
import co.alianza.demo.common.utils.exceptions.EntityAlreadyFoundException;
import co.alianza.demo.common.utils.exceptions.EntityNotFoundException;
import co.alianza.demo.services.interfaces.IClientService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClientService implements IClientService {

  private static final String CLIENT_NOT_FOUND = "Client not found";

  private List<ClientDto> clients = loadClients();

  @Override
  public List<ClientDto> getAll() {
    return clients;
  }

  @Override
  public ClientDto getById(Long id) {
    log.info("Getting client with id: {}", id);
    var client = clients.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
    if (client == null) {
      log.info(CLIENT_NOT_FOUND);
      throw new EntityNotFoundException(CLIENT_NOT_FOUND);
    }
    return client;
  }

  @Override
  public ClientDto create(ClientDto clientDto) {
    log.info("Creating client with username: {}", clientDto.getUsername());
    var exits = clients.stream().filter(client -> client.getUsername().equals(clientDto.getUsername())).findFirst();
    if (exits.isPresent()) {
      log.info("Client already exists");
      throw new EntityAlreadyFoundException("Client already exists");
    }
    clientDto.setEndDate(null);
    clientDto.setId((long) (clients.size() + 1));
    clients.add(clientDto);
    return clientDto;
  }

  @Override
  public boolean update(ClientDto clientDto) {
    try {
      var client = clients.stream().filter(x -> x.getId().equals(clientDto.getId())).findFirst().orElse(null);
      if (client == null) {
        log.info(CLIENT_NOT_FOUND);
        throw new EntityNotFoundException(CLIENT_NOT_FOUND);
      }
      client.setName(clientDto.getName());
      client.setEmail(clientDto.getEmail());
      client.setPhone(clientDto.getPhone());
      client.setStartDate(clientDto.getStartDate());
      client.setEndDate(clientDto.getEndDate());
      return true;
    } catch (Exception ex) {
      log.error("Error updating client with id: {}", clientDto.getId(), ex);
      return false;
    }
  }

  @Override
  public void delete(Long id) {
    try {
      clients.removeIf(client -> client.getId().equals(id));
      log.info("Client deleted successfully");
    } catch (Exception ex) {
      log.error("Error deleting client with id: {}", id, ex);
    }
  }

  private List<ClientDto> loadClients() {
    return new ArrayList<>(List.of(
      ClientDto.builder().id(1L).username("jgutierrez").name("Juliana Rodriguez").email("juliana@example.com").phone("321987654321").startDate(new Date()).endDate(null).build(),
      ClientDto.builder().id(2L).username("jcarrillo").name("Juan Carrillo").email("juan@example.com").phone("321987654521").startDate(new Date()).endDate(null).build(),
      ClientDto.builder().id(3L).username("cariza").name("Carlos Ariza").email("cariza@example.com").phone("321986654320").startDate(new Date()).endDate(null).build()));
  }

}
