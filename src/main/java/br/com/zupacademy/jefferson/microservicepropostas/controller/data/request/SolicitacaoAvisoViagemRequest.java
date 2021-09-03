package br.com.zupacademy.jefferson.microservicepropostas.controller.data.request;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class SolicitacaoAvisoViagemRequest {

    @NotBlank
    private String destino;

    @NotNull
    @Future
    private LocalDate validoAte;

    public SolicitacaoAvisoViagemRequest(String destino, LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }
}
