package co.alianza.demo.controllers;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.alianza.demo.common.dtos.ClientDto;
import co.alianza.demo.services.interfaces.IClientService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/clients", produces = "application/json")
@Validated
public class ClientsController {

  private IClientService clientService;

  ClientsController(IClientService clientService) {
    this.clientService = clientService;
  }

  // GET /clients
  @GetMapping
  public List<ClientDto> getAll(@RequestParam(required = false) String username) {
    return clientService.getAll(username);
  }

  // GET /clients/{id}
  @GetMapping("/{id}")
  public ClientDto getById(@PathVariable(required = true) Long id) {
    return clientService.getById(id);
  }

  // POST /clients
  @PostMapping
  public ClientDto create(@Valid @RequestBody(required = true) ClientDto clientDto) {
    return clientService.create(clientDto);
  }

  // PUT /clients
  @PutMapping
  public boolean update(@Valid @RequestBody(required = true) ClientDto clientDto) {
    return clientService.update(clientDto);
  }

  // DELETE /clients/{id}
  @DeleteMapping("/{id}")
  public void delete(@PathVariable(required = true) Long id) {
    clientService.delete(id);
  }
}
