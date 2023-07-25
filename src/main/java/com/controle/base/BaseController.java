package com.controle.base;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public class BaseController<ENTITY extends BaseEntity, REPOSITORY extends BaseRepository<ENTITY>, SERVICE extends BaseService<ENTITY, REPOSITORY>> {

  @Autowired
  private SERVICE service;
  
  @Autowired
  private REPOSITORY repo;

  @PostMapping
  public ResponseEntity<String> salvar(@RequestBody ENTITY entity){
    Optional<ENTITY> result = service.salvar(entity);
    if(result.isPresent()){
      return ResponseEntity.status(HttpStatus.CREATED).body(result.get().getCod().toString());
    } else {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
  }

  @GetMapping
  public List<ENTITY> findAll(){
    return repo.findAll();
  }

  @PutMapping
  public ResponseEntity<ENTITY> atualizar(@RequestBody ENTITY entity){
    ENTITY entidadeAtualizar = service.chamarAtualizar(entity);
    return ResponseEntity.ok().body(entidadeAtualizar);
  }

  @DeleteMapping
  public ResponseEntity<Void> delete(@RequestParam("cod")Long cod){
    service.delete(cod);
    return ResponseEntity.noContent().build();
  }
}
