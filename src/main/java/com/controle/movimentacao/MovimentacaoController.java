package com.controle.movimentacao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.controle.base.BaseController;

@RestController
@RequestMapping("/api/movimentacoes")
public class MovimentacaoController extends BaseController<Movimentacao, MovimentacaoRepository, MovimentacaoService>{

  @Autowired
  private MovimentacaoRepository movimentacaoRepository;

  @Autowired
  private MovimentacaoService movimentacaoService;

  @PostMapping(value = "/salvarMovimentacao")
  public ResponseEntity<String> salvarMovimentacao(@RequestBody Movimentacao movimentacao){
    Optional<Movimentacao> result = movimentacaoService.salvarMovimentacao(movimentacao);
    if(result.isPresent()){
      return ResponseEntity.status(HttpStatus.CREATED).body(result.get().getCod().toString());
    } else {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
  }

  @GetMapping(value = "/listarMovimentacoesAgrupadasPorClienteETipoMovimentacao")
  public Map<Object, Object> listarMovimentacoesAgrupadasPorClienteETipoMovimentacao(){
    return movimentacaoService.listarMovimentacoesAgrupadasPorClienteETipoMovimentacao();
  }

  @GetMapping(value = "/listarMovimentacoesPorCliente")
  public List<Movimentacao> listarMovimentacoesPorCliente(@RequestParam("cod") Long codCliente){
    return movimentacaoRepository.listarMovimentacoesPorCliente(codCliente);
  }
  
}
