package br.com.zupacademy.jefferson.microservicepropostas.entity;

import br.com.zupacademy.jefferson.microservicepropostas.enums.StatusCarteira;
import br.com.zupacademy.jefferson.microservicepropostas.enums.TipoCarteira;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_carteira_digital")
public class CarteiraDigital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carteira_digital")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_carteira_carteira_digital")
    private TipoCarteira tipoCarteira;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status_carteira_carteira_digital")
    private StatusCarteira statusCarteira;

    @NotNull
    @Column(name = "associada_em_carteira_digital")
    private LocalDateTime associadaEm;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public CarteiraDigital() {
    }

    public CarteiraDigital(TipoCarteira tipoCarteira, Cartao cartao) {
        this.tipoCarteira = tipoCarteira;
        this.associadaEm = LocalDateTime.now();
        this.statusCarteira = StatusCarteira.ATIVO;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public TipoCarteira getTipoCarteira() {
        return tipoCarteira;
    }

}
