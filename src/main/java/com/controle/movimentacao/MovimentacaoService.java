package com.controle.movimentacao;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controle.base.BaseService;
import com.controle.resumo.MovimentacaoResumida;

@Service
public class MovimentacaoService extends BaseService<Movimentacao, MovimentacaoRepository> {
  
  @Autowired
  private MovimentacaoRepository movimentacaoRepository;

  public Optional<Movimentacao> salvarMovimentacao(Movimentacao movimentacao){
    validarDataHora(movimentacao);
    Movimentacao nova = movimentacaoRepository.save(movimentacao);
    return Optional.of(nova);
  }

  public void validarDataHora(Movimentacao movimentacao){
    LocalDateTime agora = LocalDateTime.now();
    LocalDateTime datahorai = movimentacao.getDatahorai();
    LocalDateTime datahoraf = movimentacao.getDatahoraf();

    if((datahorai.isBefore(agora)) || (datahoraf.isBefore(agora))){
      throw new RuntimeException("Data e hora inicial ou final estão menor que a atual do sistema!");
    } else if(datahoraf.isBefore(datahorai)){
      throw new RuntimeException("Data e hora final menor que a inicial");
    }
  }

  public Map<Object, Object> listarMovimentacoesAgrupadasPorClienteETipoMovimentacao(){
    List<MovimentacaoResumida> moviementacoes = movimentacaoRepository.listarMovimentacoesAgrupadasPorClienteETipoMovimentacao();

    Map<Object, Object> firstMap = moviementacoes.stream().collect(Collectors.toMap(x -> x.getCod(), x -> x));

    Optional<Long> totalImportacao = Optional.empty();
    Optional<Long> totalExportacao = Optional.empty();

    totalImportacao = Optional.of(moviementacoes.stream().mapToLong(m -> m.getTotalImportacao()).sum());
    totalExportacao = Optional.of(moviementacoes.stream().mapToLong(m -> m.getTotalExportacao()).sum());

    Map<Object, Object> secondMap = new HashMap<>();
    if((totalImportacao.isPresent()) && (totalExportacao.isPresent())){
      secondMap.put("Total de Importação", totalImportacao);
      secondMap.put("Total de Exportação", totalExportacao);
    }

    Map<Object, Object> finalMap = new LinkedHashMap<>(firstMap);
    finalMap.putAll(secondMap);

    return finalMap;
  }
}
