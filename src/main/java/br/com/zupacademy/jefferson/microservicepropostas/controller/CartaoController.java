package br.com.zupacademy.jefferson.microservicepropostas.controller;

import br.com.zupacademy.jefferson.microservicepropostas.controller.data.request.BiometriaRequest;
import br.com.zupacademy.jefferson.microservicepropostas.entity.Biometria;
import br.com.zupacademy.jefferson.microservicepropostas.entity.Cartao;
import br.com.zupacademy.jefferson.microservicepropostas.repository.BiometriaRepository;
import br.com.zupacademy.jefferson.microservicepropostas.repository.CartaoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    private CartaoRepository cartaoRepository;

    private BiometriaRepository biometriaRepository;

    public CartaoController(CartaoRepository cartaoRepository, BiometriaRepository biometriaRepository) {
        this.cartaoRepository = cartaoRepository;
        this.biometriaRepository = biometriaRepository;
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
}
