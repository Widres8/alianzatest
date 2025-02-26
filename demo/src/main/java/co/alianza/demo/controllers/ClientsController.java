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
import org.springframework.web.bind.annotation.RestController;

import co.alianza.demo.common.dtos.ClientDto;
import co.alianza.demo.services.interfaces.IClientService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/clients")
@Validated
public class ClientsController {

  private IClientService clientService;

  ClientsController(IClientService clientService) {
    this.clientService = clientService;
  }

  // GET /clients
  // http://localhost:8080/clients
  @GetMapping
  public List<ClientDto> getAll() {
    return clientService.getAll();
  }

  // GET /clients/{id}
  // http://localhost:8080/clients/1
  @GetMapping("/{id}")
  public ClientDto getById(@PathVariable(required = true) Long id) {
    return clientService.getById(id);
  }

  // POST /clients
  // http://localhost:8080/clients
  // {
  // "username": "acortez",
  // "name": "Ariel Cortez",
  // "email": "acortez@example.com",
  // "phone": "30015278925",
  // "startDate": "2025-02-26T02:19:58.467+00:00"
  // }
  @PostMapping
  public ClientDto create(@Valid @RequestBody(required = true) ClientDto clientDto) {
    return clientService.create(clientDto);
  }

  // PUT /clients
  // http://localhost:8080/clients
  // {
  // "username": "acortez",
  // "name": "Ariel Cortez",
  // "email": "acortez@example.com",
  // "phone": "30015278925",
  // "startDate": "2025-02-26T02:19:58.467+00:00"
  // }
  @PutMapping
  public boolean update(@Valid @RequestBody(required = true) ClientDto clientDto) {
    return clientService.update(clientDto);
  }

  // DELETE /clients/{id}
  // http://localhost:8080/clients/1
  @DeleteMapping("/{id}")
  public void delete(@PathVariable(required = true) Long id) {
    clientService.delete(id);
  }
}
