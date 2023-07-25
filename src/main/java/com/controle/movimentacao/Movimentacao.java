package com.controle.movimentacao;

import java.time.LocalDateTime;

import com.controle.base.BaseEntity;
import com.controle.conteiner.Conteiner;
import com.controle.enums.TipoMovimentacao;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table
public class Movimentacao extends BaseEntity {

  @OneToOne
  @JoinColumn(name = "conteiner_cod")
  private Conteiner conteiner;

  @Enumerated(EnumType.STRING)
  private TipoMovimentacao tipom;

  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime datahorai;

  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime datahoraf;

  public Movimentacao(){}

  public Conteiner getConteiner() {
    return conteiner;
  }

  public void setConteiner(Conteiner conteiner) {
    this.conteiner = conteiner;
  }

  public TipoMovimentacao getTipom() {
    return tipom;
  }

  public void setTipom(TipoMovimentacao tipom) {
    this.tipom = tipom;
  }

  public LocalDateTime getDatahorai() {
    return datahorai;
  }

  public void setDatahorai(LocalDateTime datahorai) {
    this.datahorai = datahorai;
  }

  public LocalDateTime getDatahoraf() {
    return datahoraf;
  }

  public void setDatahoraf(LocalDateTime datahoraf) {
    this.datahoraf = datahoraf;
  }

  
  
}
