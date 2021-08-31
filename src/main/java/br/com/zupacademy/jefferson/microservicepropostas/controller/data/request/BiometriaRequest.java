package br.com.zupacademy.jefferson.microservicepropostas.controller.data.request;

import br.com.zupacademy.jefferson.microservicepropostas.entity.Biometria;
import br.com.zupacademy.jefferson.microservicepropostas.entity.Cartao;

import javax.validation.constraints.NotBlank;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class BiometriaRequest {

    @NotBlank
    private String digital;

    @Deprecated
    public BiometriaRequest() {
    }

    public BiometriaRequest(String digital) {
        this.digital = digital;
    }

    public String getDigital() {
        return digital;
    }

    public Biometria convertRequestToEntity(Cartao cartao) {
        return new Biometria(Base64.getEncoder().encodeToString(digital.getBytes(StandardCharsets.UTF_8)), cartao);
    }

}
