package br.com.zupacademy.jefferson.microservicepropostas.controller.data.response;

import java.time.LocalDate;

public class AvisoViagemApiResponse {

    private LocalDate validoAte;

    private String destino;

    public AvisoViagemApiResponse(LocalDate validoAte, String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }
}
