package br.com.zupacademy.jefferson.microservicepropostas.controller.data.response;

import java.time.LocalDateTime;

public class BloqueioCartaoApiResponse {

    private String id;
    private LocalDateTime bloqueadoEm;
    private String sistemaResponsavel;
    private Boolean ativo;

    public BloqueioCartaoApiResponse(String id, LocalDateTime bloqueadoEm, String sistemaResponsavel, Boolean ativo) {
        this.id = id;
        this.bloqueadoEm = bloqueadoEm;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getBloqueadoEm() {
        return bloqueadoEm;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    public Boolean getAtivo() {
        return ativo;
    }
}
