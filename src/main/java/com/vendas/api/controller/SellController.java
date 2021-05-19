package com.vendas.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.vendas.api.model.Sell;
import com.vendas.api.service.SellService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/venda")
public class SellController {
  
  @Autowired
  private SellService sellService;

  @RequestMapping(method = RequestMethod.GET)
  public List<Sell> getAll() {
    return sellService.getAll();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<Optional<Sell>> getById(@PathVariable(value = "id") Long id) {
    return Optional
            .ofNullable( sellService.getById(id) )
            .map( sell -> ResponseEntity.ok().body(sell) )
            .orElseGet( () -> ResponseEntity.notFound().build() );
  }

  @RequestMapping(method = RequestMethod.POST)
  public Sell save(@Valid @RequestBody Sell sell) {
    return sellService.insert(sell);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public ResponseEntity<Sell> update(@Valid @PathVariable(value = "id") Long id, @RequestBody Sell editSell) {
    return Optional
            .ofNullable( sellService.update(id, editSell) )
            .map( product -> ResponseEntity.ok().body(product) )
            .orElseGet( () -> ResponseEntity.notFound().build() );
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void delete(@PathVariable(value = "id") Long id) {
    sellService.delete(id);
  }
}
