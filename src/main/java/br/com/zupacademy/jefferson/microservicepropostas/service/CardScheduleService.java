package br.com.zupacademy.jefferson.microservicepropostas.service;

import br.com.zupacademy.jefferson.microservicepropostas.controller.ApiCardClient;
import br.com.zupacademy.jefferson.microservicepropostas.controller.data.response.CardApiResponse;
import br.com.zupacademy.jefferson.microservicepropostas.entity.Cartao;
import br.com.zupacademy.jefferson.microservicepropostas.entity.NovaProposta;
import br.com.zupacademy.jefferson.microservicepropostas.repository.NovaPropostaRepository;
import feign.FeignException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardScheduleService {

    private NovaPropostaRepository novaPropostaRepository;

    private ApiCardClient cardClient;

    public CardScheduleService(NovaPropostaRepository novaPropostaRepository, ApiCardClient cardClient) {
        this.novaPropostaRepository = novaPropostaRepository;
        this.cardClient = cardClient;
    }

    @Scheduled(fixedDelayString = "${fixedDelay.in.milliseconds}")
    public void generateCardWithProposal (){
        List<NovaProposta> proposesWithoutCard = novaPropostaRepository.findProposesWithoutCard();

        proposesWithoutCard.forEach(novaProposta -> {
            try{
                CardApiResponse cardApiResponse = cardClient.associateCardWithProposal(novaProposta.getId().toString());
                Cartao cartao = cardApiResponse.convertResponseToEntity();
                novaProposta.associateCard(cartao);
                novaPropostaRepository.save(novaProposta);
            }catch (FeignException f){
                System.out.println("Não foi possivel associar cartão a proposta.");
                f.getMessage();
            }
        });

    }
}
