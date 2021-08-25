package br.com.zupacademy.jefferson.microservicepropostas.controller;

import br.com.zupacademy.jefferson.microservicepropostas.controller.data.request.SolicitacaoAnaliseRequest;
import br.com.zupacademy.jefferson.microservicepropostas.controller.data.response.ResultadoAnaliseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(url = "${solicitacao-analise.host}", name = "solicitacaoAnalise")
public interface SolicitacaoAnaliseClient {

    @PostMapping("${solicitacao-analise.proposta}")
    ResultadoAnaliseResponse consultaSolicitacao(@RequestBody @Valid SolicitacaoAnaliseRequest request);
}
