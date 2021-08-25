package br.com.zupacademy.jefferson.microservicepropostas.controller;

import br.com.zupacademy.jefferson.microservicepropostas.controller.data.response.CardApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "accounts", url = "${api-cartao.host}")
public interface ApiCardClient {

    @GetMapping("${api-cartao.associate-card}")
    CardApiResponse associateCardWithProposal(@RequestParam String idProposta);
}
