package com.vendas.api.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Data;

@Data
@Entity()
@Table(name = "products")
@SQLDelete(sql = "UPDATE products SET delete_at = NOW() WHERE id=?")
@Where(clause = "delete_at IS NULL")
@JsonFormat(pattern = "dd/MM/yyyy")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  @NotBlank(message = "O campo name é obrigatório")
  private String name;

  @Column(nullable = false)
  @NotNull(message = "O campo price é obrigatório")
  private Double price;

  @Column(nullable = true)
  @Temporal(TemporalType.TIMESTAMP)
  @JsonIgnore
  private Date deleteAt;

  @OneToMany(mappedBy = "product")
  @JsonIgnore
  private List<Sell> seels;
}
