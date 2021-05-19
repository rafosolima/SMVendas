package com.vendas.api.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity()
@Table(name = "sells")
@SQLDelete(sql = "UPDATE sells SET delete_at = NOW() WHERE id=?")
@Where(clause = "delete_at IS NULL")
public class Sell {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
  @NotNull(message = "O campo date é obrigatório")
  private Date date;

  @Column(nullable = false)
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
  private Date orderDelivery;

  @Column(nullable = true)
  @Temporal(TemporalType.TIMESTAMP)
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
  @JsonIgnore
  private Date deleteAt;

  @ManyToOne()
  @JoinColumn(name = "client_id", nullable = false)
  @NotNull(message = "O objeto Client contendo o id é obrigatório")
  private Client client;

  @ManyToOne()
  @JoinColumn(name = "product_id", nullable = false)
  @NotNull(message = "O objeto Product contendo o id é obrigatório")
  private Product product;
}
