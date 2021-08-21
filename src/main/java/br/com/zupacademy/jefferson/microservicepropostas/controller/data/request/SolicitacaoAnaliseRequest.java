package br.com.zupacademy.jefferson.microservicepropostas.controller.data.request;

import br.com.zupacademy.jefferson.microservicepropostas.entity.NovaProposta;
import br.com.zupacademy.jefferson.microservicepropostas.validation.CpfOuCnpj;

import javax.validation.constraints.NotBlank;

public class SolicitacaoAnaliseRequest {

    @NotBlank
    @CpfOuCnpj
    private String documento;

    @NotBlank
    private String nome;

    @NotBlank
    private String idProposta;

    public SolicitacaoAnaliseRequest(NovaProposta novaProposta) {
        this.documento = novaProposta.getDocumento();
        this.nome = novaProposta.getNome();
        this.idProposta = novaProposta.getId().toString();
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }

}
