package com.controle.base;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class BaseService<ENTITY extends BaseEntity, REPOSITORY extends BaseRepository<ENTITY>> {
  
  @Autowired
  private REPOSITORY repo;

  @Transactional
  public Optional<ENTITY> salvar(ENTITY entity){
    ENTITY n = repo.save(entity);
    return Optional.of(n);
  }

  @Transactional
  public Optional<ENTITY> atualizar(ENTITY entity){
    if(repo.existsById(entity.getCod())){
      salvar(entity);
      return Optional.of(entity);
    } else {
      return Optional.empty();
    }
  }

  public ENTITY chamarAtualizar(ENTITY entity){
    return atualizar(entity).orElseThrow(() -> new RuntimeException("Registro não encontrado!" + entity.getCod()));
  }

  @Transactional
  public void delete(Long id){
    if(repo.existsById(id)){
      repo.deleteById(id);
    }
  }
}
