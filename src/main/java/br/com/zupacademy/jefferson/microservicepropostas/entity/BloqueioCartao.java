package br.com.zupacademy.jefferson.microservicepropostas.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_bloqueio_cartao")
public class BloqueioCartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bloqueio_cartao")
    private Long id;

    @NotNull
    @Column(name = "instante_bloqueio_cartao")
    private LocalDateTime bloqueadoEm;

    @NotBlank
    @Column(name = "ip_client_bloqueio_cartao")
    private String ipClient;

    @NotBlank
    @Column(name = "user_agent_bloqueio_cartao")
    private String userAgent;

    @ManyToOne
    private Cartao cartao;

    public BloqueioCartao(String ipClient, String userAgent, Cartao cartao) {
        this.ipClient = ipClient;
        this.userAgent = userAgent;
        this.cartao = cartao;
        this.bloqueadoEm = LocalDateTime.now();
    }
}
