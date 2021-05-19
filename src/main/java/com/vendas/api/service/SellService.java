package com.vendas.api.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.vendas.api.model.Sell;
import com.vendas.api.repository.SellRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellService {
  @Autowired
  private SellRepository sellRepository;

  public List<Sell> getAll() {
    return sellRepository.findAll();
  }

  public Optional<Sell> getById(Long id) {
    return sellRepository.findById(id);
  }

  public Sell insert(Sell sellSave) {
    sellSave.setOrderDelivery(
      Date.from(
        LocalDateTime.from(
          sellSave.getDate().toInstant().atZone(ZoneId.of("UTC"))
        ).plusDays(10).atZone(
          ZoneId.systemDefault()
        ).toInstant()
      )
    );
    return sellRepository.save(sellSave);
  }

  public Sell update(Long id, Sell newSell) {
    Optional<Sell> oldSell = sellRepository.findById(id);
    if(!oldSell.isPresent()) {
      return null;
    }
    Sell sell = oldSell.get();
    sell.setDate(newSell.getDate() != null ? newSell.getDate() : sell.getDate());
    sell.setOrderDelivery(
      Date.from(
        LocalDateTime.from(
          sell.getDate().toInstant().atZone(ZoneId.of("UTC"))
        ).plusDays(10).atZone(
          ZoneId.systemDefault()
        ).toInstant()
      )
    );
    return sellRepository.save(sell);
  }

  public void delete(Long id) {
    sellRepository.deleteById(id);
  }
}
