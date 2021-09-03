package br.com.zupacademy.jefferson.microservicepropostas.controller.data.request;

import br.com.zupacademy.jefferson.microservicepropostas.entity.Cartao;
import br.com.zupacademy.jefferson.microservicepropostas.entity.CarteiraDigital;
import br.com.zupacademy.jefferson.microservicepropostas.enums.TipoCarteira;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AssociaCarteiraDigitalRequest {

    @NotBlank
    @Email
    private String email;
    @NotNull
    private TipoCarteira tipoCarteira;

    public AssociaCarteiraDigitalRequest(String email, TipoCarteira tipoCarteira) {
        this.email = email;
        this.tipoCarteira = tipoCarteira;
    }

    public String getEmail() {
        return email;
    }

    public TipoCarteira getTipoCarteira() {
        return tipoCarteira;
    }

    public CarteiraDigital convertRequestToEntity(Cartao cartao) {
        return new CarteiraDigital(tipoCarteira, cartao);
    }
}
