package br.com.zupacademy.jefferson.microservicepropostas.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_biometria")
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_biometria")
    private Long id;

    @NotBlank
    @Column(name = "digital_biometria")
    private String digital;

    @NotNull
    @Column(name = "data_criacao_biometria")
    private LocalDateTime dataCriação;

    @ManyToOne
    private Cartao cartao;

    public Biometria(String digital, Cartao cartao) {
        this.digital = digital;
        this.dataCriação = LocalDateTime.now();
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public String getDigital() {
        return digital;
    }

    public LocalDateTime getDataCriação() {
        return dataCriação;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
