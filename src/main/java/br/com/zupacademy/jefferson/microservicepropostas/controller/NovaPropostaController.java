package br.com.zupacademy.jefferson.microservicepropostas.controller;

import br.com.zupacademy.jefferson.microservicepropostas.controller.data.request.NovaPropostaRequest;
import br.com.zupacademy.jefferson.microservicepropostas.controller.data.request.SolicitacaoAnaliseRequest;
import br.com.zupacademy.jefferson.microservicepropostas.controller.data.response.ResultadoAnaliseResponse;
import br.com.zupacademy.jefferson.microservicepropostas.entity.NovaProposta;
import br.com.zupacademy.jefferson.microservicepropostas.enums.ResultadoSolicitacao;
import br.com.zupacademy.jefferson.microservicepropostas.exception.DocumentDuplicationException;
import br.com.zupacademy.jefferson.microservicepropostas.repository.NovaPropostaRepository;
import feign.FeignException;
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

    private SolicitacaoAnaliseClient solicitacaoAnaliseClient;

    public NovaPropostaController(NovaPropostaRepository novaPropostaRepository, SolicitacaoAnaliseClient solicitacaoAnaliseClient) {
        this.novaPropostaRepository = novaPropostaRepository;
        this.solicitacaoAnaliseClient = solicitacaoAnaliseClient;
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

        try{
            SolicitacaoAnaliseRequest solicitacaoAnaliseRequest = new SolicitacaoAnaliseRequest(propostaSalva);
            ResultadoAnaliseResponse resultadoAnaliseResponse = solicitacaoAnaliseClient.consultaSolicitacao(solicitacaoAnaliseRequest);
            propostaSalva.atualizaStatus(resultadoAnaliseResponse.getResultadoSolicitacao());
        }catch (FeignException fe){
            propostaSalva.atualizaStatus(ResultadoSolicitacao.COM_RESTRICAO);
        }
        novaPropostaRepository.save(propostaSalva);

        URI location = uriBuilder.path("nova-proposta/{id}").build(propostaSalva.getId());
        return ResponseEntity.created(location).build();
    }
}
