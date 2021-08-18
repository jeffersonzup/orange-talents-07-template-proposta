package br.com.zupacademy.jefferson.microservicepropostas.controller;

import br.com.zupacademy.jefferson.microservicepropostas.controller.data.request.NovaPropostaRequest;
import br.com.zupacademy.jefferson.microservicepropostas.entity.NovaProposta;
import br.com.zupacademy.jefferson.microservicepropostas.repository.NovaPropostaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/nova-proposta")
public class NovaPropostaController {

    private NovaPropostaRepository novaPropostaRepository;

    public NovaPropostaController(NovaPropostaRepository novaPropostaRepository) {
        this.novaPropostaRepository = novaPropostaRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrarNovaProposta(@RequestBody @Valid NovaPropostaRequest request, UriComponentsBuilder uriBuilder){
        NovaProposta novaProposta = request.converterRequestToEntity();
        NovaProposta propostaSalva = novaPropostaRepository.save(novaProposta);
        URI location = uriBuilder.path("nova-proposta/{id}").build(propostaSalva.getId());
        return ResponseEntity.created(location).build();
    }
}
