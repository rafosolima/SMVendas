package com.vendas.api.repository;

import org.springframework.stereotype.Repository;

import com.vendas.api.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  
}
