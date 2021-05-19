package com.vendas.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.vendas.api.model.Client;
import com.vendas.api.service.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cliente")
public class ClientController {
  
  @Autowired
  private ClientService clientService;

  @RequestMapping(method = RequestMethod.GET)
  public List<Client> getAll() {
    return clientService.getAll();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<Optional<Client>> getById(@PathVariable(value = "id") Long id) {
    return Optional
            .ofNullable( clientService.getById(id) )
            .map( client -> ResponseEntity.ok().body(client) )
            .orElseGet( () -> ResponseEntity.notFound().build() );
  }

  @RequestMapping(method = RequestMethod.POST)
  public Client save(@Valid @RequestBody Client client) {
    return clientService.insert(client);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public ResponseEntity<Client> update(@Valid @PathVariable(value = "id") Long id, @RequestBody Client editClient) {
    return Optional
            .ofNullable( clientService.update(id, editClient) )
            .map( client -> ResponseEntity.ok().body(client) )
            .orElseGet( () -> ResponseEntity.notFound().build() );
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void delete(@PathVariable(value = "id") Long id) {
    clientService.delete(id);
  }
}
