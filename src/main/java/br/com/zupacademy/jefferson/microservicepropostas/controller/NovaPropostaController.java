package br.com.zupacademy.jefferson.microservicepropostas.controller;

import br.com.zupacademy.jefferson.microservicepropostas.controller.data.request.NovaPropostaRequest;
import br.com.zupacademy.jefferson.microservicepropostas.entity.NovaProposta;
import br.com.zupacademy.jefferson.microservicepropostas.exception.DocumentDuplicationException;
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
        boolean existeDocumento = novaPropostaRepository.findByDocumento(novaProposta.getDocumento())
                .stream()
                .anyMatch(documentoExistente -> !documentoExistente.equals(novaProposta));
        if(existeDocumento){
            throw new DocumentDuplicationException("Não foi possível realizar o cadastro de nova proposta, duplicidade de documento.");
        }
        NovaProposta propostaSalva = novaPropostaRepository.save(novaProposta);
        URI location = uriBuilder.path("nova-proposta/{id}").build(propostaSalva.getId());
        return ResponseEntity.created(location).build();
    }
}
