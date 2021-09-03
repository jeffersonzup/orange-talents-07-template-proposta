package br.com.zupacademy.jefferson.microservicepropostas.controller.data.request;

import br.com.zupacademy.jefferson.microservicepropostas.enums.TipoCarteira;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SolicitacaoInclusaoCarteiraRequest {

    @NotBlank
    @Email
    private String email;

    @NotNull
    private TipoCarteira carteira;

    public SolicitacaoInclusaoCarteiraRequest(String email, TipoCarteira carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public TipoCarteira getCarteira() {
        return carteira;
    }
}
