package br.com.zupacademy.jefferson.microservicepropostas.controller.data.request;

import br.com.zupacademy.jefferson.microservicepropostas.entity.NovaProposta;
import br.com.zupacademy.jefferson.microservicepropostas.config.validation.CpfOuCnpj;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class NovaPropostaRequest {

    @NotBlank
    @CpfOuCnpj
    private String documento;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String endereco;

    @NotNull
    @Positive
    private BigDecimal salario;

    public NovaPropostaRequest(String documento, String email, String nome, String endereco, BigDecimal salario) {
        this.documento = documento.replaceAll("\\D", "");
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "NovaPropostaRequest{" +
                "documento='" + documento + '\'' +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", salario=" + salario +
                '}';
    }

    public NovaProposta converterRequestToEntity() {
        return new NovaProposta(this.documento, this.email, this.nome, this.endereco, this.salario);
    }
}
