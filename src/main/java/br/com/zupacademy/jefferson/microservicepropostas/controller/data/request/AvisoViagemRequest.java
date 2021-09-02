package br.com.zupacademy.jefferson.microservicepropostas.controller.data.request;

import br.com.zupacademy.jefferson.microservicepropostas.entity.AvisoViagem;
import br.com.zupacademy.jefferson.microservicepropostas.entity.Cartao;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoViagemRequest {

    @NotBlank
    private String destinoViagem;
    @NotNull
    @Future
    private LocalDate terminoViagem;

    public AvisoViagemRequest(String destinoViagem, LocalDate terminoViagem) {
        this.destinoViagem = destinoViagem;
        this.terminoViagem = terminoViagem;
    }

    public String getDestinoViagem() {
        return destinoViagem;
    }

    public LocalDate getTerminoViagem() {
        return terminoViagem;
    }

    public AvisoViagem convertRequestToEntity(String ipClient, String userAgent, Cartao cartao) {
        return new AvisoViagem(destinoViagem, terminoViagem, ipClient, userAgent, cartao);
    }
}
