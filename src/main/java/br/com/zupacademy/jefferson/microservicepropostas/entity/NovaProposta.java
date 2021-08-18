package br.com.zupacademy.jefferson.microservicepropostas.entity;

import br.com.zupacademy.jefferson.microservicepropostas.validation.CpfOuCnpj;

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
    @CpfOuCnpj
    @Column(name = "documento_proposta", nullable = false)
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

    public NovaProposta(String documento, String email, String nome, String endereco, BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Long getId() {
        return id;
    }
}
