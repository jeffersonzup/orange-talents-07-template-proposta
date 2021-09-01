package br.com.zupacademy.jefferson.microservicepropostas.entity;

import br.com.zupacademy.jefferson.microservicepropostas.enums.StatusCartao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_cartao")
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cartao")
    private Long id;

    @Column(name = "numero_cartao")
    private String numeroCartao;

    @Column(name = "data_emissao_cartao")
    private LocalDateTime emitidoEm;

    @Column(name = "titular_cartao")
    private String titular;

    @Column(name = "limite_cartao")
    private BigDecimal limite;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status_cartao")
    private StatusCartao statusCartao;

    public Cartao() {
    }

    public Cartao(String numeroCartao, LocalDateTime emitidoEm, String titular, BigDecimal limite) {
        this.numeroCartao = numeroCartao;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
        this.statusCartao = StatusCartao.ATIVO;
    }

    public StatusCartao getStatusCartao() {
        return statusCartao;
    }

    public void bloqueiaCartao() {
        this.statusCartao = StatusCartao.BLOQUEADO;
    }

    public Long getId() {
        return id;
    }
}
