package br.com.zupacademy.jefferson.microservicepropostas.entity;

import br.com.zupacademy.jefferson.microservicepropostas.config.security.Cryptography;
import br.com.zupacademy.jefferson.microservicepropostas.enums.ResultadoSolicitacao;
import br.com.zupacademy.jefferson.microservicepropostas.enums.StatusProposta;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_nova_proposta")
public class NovaProposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proposta")
    private Long id;

    @NotBlank
    @Column(name = "documento_proposta", nullable = false, unique = true)
    private String documento;

    @Email
    @NotBlank
    @Column(name = "email_proposta", nullable = false)
    private String email;

    @NotBlank
    @Column(name = "nome_proposta", nullable = false)
    private String nome;

    @NotBlank
    @Column(name = "endereco_proposta", nullable = false)
    private String endereco;

    @NotNull
    @Positive
    @Column(name = "salario_proposta", nullable = false)
    private BigDecimal salario;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusProposta statusProposta;

    @OneToOne(cascade = CascadeType.MERGE)
    private Cartao cartao;

    @Deprecated
    public NovaProposta() {
    }

    public NovaProposta(String documento, String email, String nome, String endereco, BigDecimal salario) {
        this.documento = Cryptography.encrypt(documento);
        this.documento = Cryptography.hash(documento);
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
        this.statusProposta = StatusProposta.NAO_ELEGIVEL;
    }

    public void atualizaStatus(ResultadoSolicitacao resultadoSolicitacao){
        this.statusProposta = resultadoSolicitacao.getStatusResposta();
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

    public Cartao getCartao() {
        return cartao;
    }

    public void associateCard(Cartao cartao){
        this.cartao = cartao;
    }

    @Override
    public String toString() {
        return "NovaProposta{" +
                "id=" + id +
                ", documento='" + documento + '\'' +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", salario=" + salario +
                ", statusProposta=" + statusProposta +
                '}';
    }

}
