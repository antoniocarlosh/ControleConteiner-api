package com.controle.conteiner;

import com.controle.base.BaseEntity;
import com.controle.cliente.Cliente;
import com.controle.enums.Categoria;
import com.controle.enums.Status;
import com.controle.enums.TipoConteiner;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;

@Entity
@Table
public class Conteiner extends BaseEntity {

  @OneToOne  
  @JoinColumn(name = "cliente_cod")
  private Cliente cliente;

  @Pattern(regexp = "[CONT]{4}[0-9]{7}", message = "Campo deve ser preenchido com 4 letras inicias (CONT), seguidas de 7 números!")
  private String numero;

  @Enumerated(EnumType.STRING)
  private Categoria categoria;

  @Enumerated(EnumType.STRING)
  private Status status;

  @Enumerated(EnumType.STRING)
  private TipoConteiner tipoc;

  public Conteiner(){}

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public TipoConteiner getTipoc() {
    return tipoc;
  }

  public void setTipoc(TipoConteiner tipoc) {
    this.tipoc = tipoc;
  }

  
  
}
