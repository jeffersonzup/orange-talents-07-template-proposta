package br.com.zupacademy.jefferson.microservicepropostas.controller;

import br.com.zupacademy.jefferson.microservicepropostas.controller.data.request.SolicitacaoAnaliseRequest;
import br.com.zupacademy.jefferson.microservicepropostas.controller.data.response.ResultadoAnaliseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(url = "http://localhost:9999", name = "solicitacao")
public interface SolicitacaoAnaliseClient {

    @PostMapping("/api/solicitacao")
    ResultadoAnaliseResponse consultaSolicitacao(@RequestBody @Valid SolicitacaoAnaliseRequest request);
}
