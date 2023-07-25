package com.controle.cliente;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controle.base.BaseService;

@Service
public class ClienteService extends BaseService<Cliente, ClienteRepository>{
  
  @Autowired
  private ClienteRepository clienteRepository;

  public void salvarCliente(Cliente cliente){
    Optional<Cliente> cli = clienteRepository.findAll().stream().filter(cl -> cl.getNome().equals(cliente.getNome())).findFirst();
    if(!cli.isEmpty()){
      throw new RuntimeException("Cliente já cadastrado!");
    }

    clienteRepository.save(cliente);
  }
}
