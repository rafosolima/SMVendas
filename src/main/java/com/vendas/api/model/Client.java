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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Data;

@Data
@Entity()
@Table(name = "clients")
@SQLDelete(sql = "UPDATE clients SET delete_at = NOW() WHERE id=?")
@Where(clause = "delete_at IS NULL")
@JsonFormat(pattern = "dd/MM/yyyy")
public class Client {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  @NotBlank(message = "O campo name é obrigatório")
  private String name;

  @Column(nullable = false)
  @NotBlank(message = "O campo cpfCnpj é obrigatório")
  private String cpfCnpj;

  @Column(nullable = true)
  @Temporal(TemporalType.TIMESTAMP)
  @JsonIgnore
  private Date deleteAt;

  @OneToMany(mappedBy = "client")
  @JsonIgnore
  private List<Sell> seels;
}
