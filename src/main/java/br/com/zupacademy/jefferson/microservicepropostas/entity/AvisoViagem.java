package br.com.zupacademy.jefferson.microservicepropostas.entity;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_aviso_viagem")
public class AvisoViagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aviso_viagem")
    private Long id;

    @NotBlank
    @Column(name = "destino_viagem_aviso_viagem", nullable = false)
    private String destinoViagem;

    @NotNull
    @Future
    @Column(name = "termino_viagem_aviso_viagem", nullable = false)
    private LocalDate terminoViagem;

    @NotNull
    @Column(name = "data_aviso_viagem")
    private LocalDateTime avisoEm;

    @Column(name = "ip_client_aviso_viagem")
    private String ipClient;

    @Column(name = "user_agent_aviso_viagem")
    private String userAgent;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public AvisoViagem() {
    }

    public AvisoViagem(String destinoViagem, LocalDate terminoViagem, String ipClient, String userAgent, Cartao cartao) {
        this.destinoViagem = destinoViagem;
        this.terminoViagem = terminoViagem;
        this.ipClient = ipClient;
        this.userAgent = userAgent;
        this.cartao = cartao;
        this.avisoEm = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getDestinoViagem() {
        return destinoViagem;
    }

    public LocalDate getTerminoViagem() {
        return terminoViagem;
    }

    public LocalDateTime getAvisoEm() {
        return avisoEm;
    }

    public String getIpClient() {
        return ipClient;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
