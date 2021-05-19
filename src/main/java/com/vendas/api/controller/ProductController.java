package com.vendas.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.vendas.api.model.Product;
import com.vendas.api.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/produto")
public class ProductController {

  @Autowired
  private ProductService productService;

  @RequestMapping(method = RequestMethod.GET)
  public List<Product> getAll() {
    return productService.getAll();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<Optional<Product>> getById(@PathVariable(value = "id") Long id) {
    return Optional
            .ofNullable( productService.getById(id) )
            .map( product -> ResponseEntity.ok().body(product) )
            .orElseGet( () -> ResponseEntity.notFound().build() );
  }

  @RequestMapping(method = RequestMethod.POST)
  public Product save(@Valid @RequestBody Product product) {
    return productService.insert(product);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public ResponseEntity<Product> update(@Valid @PathVariable(value = "id") Long id, @RequestBody Product editProduct) {
    return Optional
            .ofNullable( productService.update(id, editProduct) )
            .map( product -> ResponseEntity.ok().body(product) )
            .orElseGet( () -> ResponseEntity.notFound().build() );
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void delete(@PathVariable(value = "id") Long id) {
    productService.delete(id);
  }
}
