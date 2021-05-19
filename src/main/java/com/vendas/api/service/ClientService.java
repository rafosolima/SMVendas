package com.vendas.api.service;

import java.util.List;
import java.util.Optional;

import com.vendas.api.model.Client;
import com.vendas.api.repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

  @Autowired
  private ClientRepository clientRepository;

  public List<Client> getAll() {
    return clientRepository.findAll();
  }

  public Optional<Client> getById(Long id) {
    return clientRepository.findById(id);
  }

  public Client insert(Client clientSave) {
    return clientRepository.save(clientSave);
  }

  public Client update(Long id, Client newClient) {
    Optional<Client> oldClient = clientRepository.findById(id);
    if(!oldClient.isPresent()) {
      return null;
    }
    Client client = oldClient.get();
    client.setName(newClient.getName() != null ? newClient.getName() : client.getName());
    client.setCpfCnpj(newClient.getCpfCnpj() != null ? newClient.getCpfCnpj() : client.getCpfCnpj());
    return clientRepository.save(client);
  }

  public void delete(Long id) {
    clientRepository.deleteById(id);
  }
}
