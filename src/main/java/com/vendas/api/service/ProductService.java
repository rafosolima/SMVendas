package com.vendas.api.service;

import java.util.List;
import java.util.Optional;

import com.vendas.api.model.Product;
import com.vendas.api.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
  @Autowired
  private ProductRepository productRepository;

  public List<Product> getAll() {
    return productRepository.findAll();
  }

  public Optional<Product> getById(Long id) {
    return productRepository.findById(id);
  }

  public Product insert(Product productSave) {
    return productRepository.save(productSave);
  }

  public Product update(Long id, Product newProduct) {
    Optional<Product> oldProduct = productRepository.findById(id);
    if(!oldProduct.isPresent()) {
      return null;
    }
    Product product = oldProduct.get();
    product.setName(newProduct.getName() != null ? newProduct.getName() : product.getName());
    product.setPrice(newProduct.getPrice() != null ? newProduct.getPrice() : product.getPrice());
    return productRepository.save(product);
  }

  public void delete(Long id) {
    productRepository.deleteById(id);
  }
}
