package br.com.zupacademy.jefferson.microservicepropostas.controller;

import br.com.zupacademy.jefferson.microservicepropostas.controller.data.request.AvisoViagemRequest;
import br.com.zupacademy.jefferson.microservicepropostas.controller.data.request.BiometriaRequest;
import br.com.zupacademy.jefferson.microservicepropostas.controller.data.request.BloqueioApiRequest;
import br.com.zupacademy.jefferson.microservicepropostas.controller.data.response.BloqueioApiResponse;
import br.com.zupacademy.jefferson.microservicepropostas.entity.AvisoViagem;
import br.com.zupacademy.jefferson.microservicepropostas.entity.Biometria;
import br.com.zupacademy.jefferson.microservicepropostas.entity.BloqueioCartao;
import br.com.zupacademy.jefferson.microservicepropostas.entity.Cartao;
import br.com.zupacademy.jefferson.microservicepropostas.enums.StatusCartao;
import br.com.zupacademy.jefferson.microservicepropostas.repository.AvisoViagemRepository;
import br.com.zupacademy.jefferson.microservicepropostas.repository.BiometriaRepository;
import br.com.zupacademy.jefferson.microservicepropostas.repository.BloqueioCartaoRepository;
import br.com.zupacademy.jefferson.microservicepropostas.repository.CartaoRepository;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    private CartaoRepository cartaoRepository;

    private BiometriaRepository biometriaRepository;

    private BloqueioCartaoRepository bloqueioCartaoRepository;

    private AvisoViagemRepository avisoViagemRepository;

    private ApiCardClient apiCardClient;

    public CartaoController(CartaoRepository cartaoRepository, BiometriaRepository biometriaRepository, BloqueioCartaoRepository bloqueioCartaoRepository, AvisoViagemRepository avisoViagemRepository, ApiCardClient apiCardClient) {
        this.cartaoRepository = cartaoRepository;
        this.biometriaRepository = biometriaRepository;
        this.bloqueioCartaoRepository = bloqueioCartaoRepository;
        this.avisoViagemRepository = avisoViagemRepository;
        this.apiCardClient = apiCardClient;
    }

    @PostMapping("/{numeroCartao}/biometria")
    @Transactional
    public ResponseEntity cadastrarBiometria(@PathVariable String numeroCartao, @RequestBody @Valid BiometriaRequest biometriaRequest, UriComponentsBuilder builder){
        Optional<Cartao> existsCartao = cartaoRepository.findByNumeroCartao(numeroCartao);

        if(existsCartao.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Cartao cartao = existsCartao.get();

        Biometria biometria = biometriaRequest.convertRequestToEntity(cartao);
        Biometria biometriaSalva = biometriaRepository.save(biometria);

        URI uri = builder.path("/cartoes/{numeroCartao}/biometria").buildAndExpand(biometriaSalva.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/{numeroCartao}/bloqueios")
    public ResponseEntity bloquearCartao(@PathVariable String numeroCartao, HttpServletRequest request){
        Optional<Cartao> existsCartao = cartaoRepository.findByNumeroCartao(numeroCartao);

        String userAgent =  request.getHeader("User-Agent");
        String ipClient = request.getHeader("X-FORWARDED-FOR");

        if(ipClient == null){
            ipClient = request.getRemoteAddr();
        }

        if (existsCartao.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Cartao cartao = existsCartao.get();

        if(cartao.getStatusCartao() == StatusCartao.BLOQUEADO){
            return ResponseEntity.unprocessableEntity().build();
        }

        BloqueioCartao solicitacaoBloqueio = new BloqueioCartao(ipClient, userAgent, cartao);

        try{
            BloqueioApiRequest bloqueioApiRequest = new BloqueioApiRequest("Proposta");
            BloqueioApiResponse bloqueioApiResponse = apiCardClient.blockCard(numeroCartao, bloqueioApiRequest);
            System.out.println(bloqueioApiResponse.getResultado());
        }catch (FeignException e){
            return ResponseEntity.internalServerError().body("Falha de comunicação com sistema externo.");
        }

        bloqueioCartaoRepository.save(solicitacaoBloqueio);
        cartao.bloqueiaCartao();
        cartaoRepository.save(cartao);

        return ResponseEntity.ok().body("Cartão Bloqueado!");
    }

    @PostMapping("/{numeroCartao}/avisos")
    public ResponseEntity avisoViagem(@PathVariable String numeroCartao, @RequestBody @Valid AvisoViagemRequest avisoViagemRequest, HttpServletRequest request){
        Optional<Cartao> existsCartao = cartaoRepository.findByNumeroCartao(numeroCartao);

        if (existsCartao.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        String ipClient = request.getHeader("X-FORWARDED-FOR");
        String userAgent = request.getHeader("User-Agent");

        if(ipClient == null){
            ipClient = request.getRemoteAddr();
        }

        Cartao cartao = existsCartao.get();

        AvisoViagem avisoViagem = avisoViagemRequest.convertRequestToEntity(ipClient, userAgent, cartao);

        AvisoViagem avisoViagemSalva = avisoViagemRepository.save(avisoViagem);

        return ResponseEntity.ok().body("Cartão foi notificado da Viagem!");
    }
}
