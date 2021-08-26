package br.com.zupacademy.jefferson.microservicepropostas.controller.data.response;

import br.com.zupacademy.jefferson.microservicepropostas.entity.NovaProposta;
import br.com.zupacademy.jefferson.microservicepropostas.enums.StatusProposta;
import java.math.BigDecimal;

public class PropostaResponse {

    private Long id;
    private String documento;
    private String email;
    private String nome;
    private String endereco;
    private BigDecimal salario;
    private StatusProposta statusProposta;

    public PropostaResponse(NovaProposta novaProposta) {
        this.id = novaProposta.getId();
        this.documento = novaProposta.getDocumento();
        this.email = novaProposta.getEmail();
        this.nome = novaProposta.getNome();
        this.endereco = novaProposta.getEndereco();
        this.salario = novaProposta.getSalario();
        this.statusProposta = novaProposta.getStatusProposta();
    }

    public Long getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public StatusProposta getStatusProposta() {
        return statusProposta;
    }

}
