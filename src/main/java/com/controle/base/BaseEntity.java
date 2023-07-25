package com.controle.base;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long cod;

  public BaseEntity(){}

  public Long getCod() {
    return cod;
  }

  @Override
  public int hashCode() {
    return cod.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    BaseEntity other = (BaseEntity) obj;
    if (cod == null) {
      if (other.cod != null)
        return false;
    } else if (!cod.equals(other.cod))
      return false;
    return true;
  }

  
}
