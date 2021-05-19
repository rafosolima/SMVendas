package com.vendas.api.repository;

import org.springframework.stereotype.Repository;

import com.vendas.api.model.Client;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
  
}
