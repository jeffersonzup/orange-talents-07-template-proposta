package br.com.zupacademy.jefferson.microservicepropostas.controller;

import br.com.zupacademy.jefferson.microservicepropostas.controller.data.request.BloqueioApiRequest;
import br.com.zupacademy.jefferson.microservicepropostas.controller.data.response.BloqueioApiResponse;
import br.com.zupacademy.jefferson.microservicepropostas.controller.data.response.CardApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "api-card", url = "${api-card.host}")
public interface ApiCardClient {

    @GetMapping("${api-card.associate-card}")
    CardApiResponse associateCardWithProposal(@RequestParam String idProposta);

    @PostMapping("${api-card.block-card}")
    BloqueioApiResponse blockCard(@PathVariable String id, @RequestBody BloqueioApiRequest bloqueioApiRequest);
}
