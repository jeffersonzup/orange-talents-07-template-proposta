package br.com.zupacademy.jefferson.microservicepropostas.controller.data.response;

import java.time.LocalDateTime;

public class CarteiraDigitalApiResponse {

    private String id;
    private String email;
    private LocalDateTime associadaEm;
    private String emissor;

    public CarteiraDigitalApiResponse(String id, String email, LocalDateTime associadaEm, String emissor) {
        this.id = id;
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getAssociadaEm() {
        return associadaEm;
    }

    public String getEmissor() {
        return emissor;
    }
}
