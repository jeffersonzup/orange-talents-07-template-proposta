package br.com.zupacademy.jefferson.microservicepropostas.controller.data.request;

import javax.validation.constraints.NotBlank;

public class BloqueioApiRequest {

    @NotBlank
    private String sistemaResponsavel;

    public BloqueioApiRequest(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
