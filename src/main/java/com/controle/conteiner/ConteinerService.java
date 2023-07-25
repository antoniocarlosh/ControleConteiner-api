package com.controle.conteiner;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controle.base.BaseService;

@Service
public class ConteinerService extends BaseService<Conteiner, ConteinerRepository> {
  
  @Autowired
  private ConteinerRepository conteinerRepository;

  public void salvarConteiner(Conteiner conteiner){
    Optional<Conteiner> cont = conteinerRepository.findAll().stream().filter(c -> c.getNumero().equals(conteiner.getNumero())).findFirst();
    if(!cont.isEmpty()){
      throw new RuntimeException("Conteiner já cadastrado no banco de dados!");
    }

    conteinerRepository.save(conteiner);
  } 
}
