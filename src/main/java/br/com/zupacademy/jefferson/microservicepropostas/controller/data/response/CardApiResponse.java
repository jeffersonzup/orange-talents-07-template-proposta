package br.com.zupacademy.jefferson.microservicepropostas.controller.data.response;

import br.com.zupacademy.jefferson.microservicepropostas.entity.Cartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CardApiResponse {

    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    private List<?> bloqueios = new ArrayList<>();
    private List<?> avisos = new ArrayList<>();
    private List<?> carteiras = new ArrayList<>();
    private List<?> parcelas = new ArrayList<>();
    private BigDecimal limite;
    private Renegociacao renegociacao;
    private Vencimento vencimento;
    private String idProposta;

    public CardApiResponse(String id, LocalDateTime emitidoEm, String titular, List<?> bloqueios, List<?> avisos, List<?> carteiras, List<?> parcelas, BigDecimal limite, Renegociacao renegociacao, Vencimento vencimento, String idProposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.bloqueios = bloqueios;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.parcelas = parcelas;
        this.limite = limite;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
        this.idProposta = idProposta;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public List<?> getBloqueios() {
        return bloqueios;
    }

    public List<?> getAvisos() {
        return avisos;
    }

    public List<?> getCarteiras() {
        return carteiras;
    }

    public List<?> getParcelas() {
        return parcelas;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public Renegociacao getRenegociacao() {
        return renegociacao;
    }

    public Vencimento getVencimento() {
        return vencimento;
    }

    public String getIdProposta() {
        return idProposta;
    }

    @Override
    public String toString() {
        return "CardApiResponse{" +
                "id='" + id + '\'' +
                ", emitidoEm=" + emitidoEm +
                ", titular='" + titular + '\'' +
                ", bloqueios=" + bloqueios +
                ", avisos=" + avisos +
                ", carteiras=" + carteiras +
                ", parcelas=" + parcelas +
                ", limite=" + limite +
                ", renegociacao=" + renegociacao +
                ", vencimento=" + vencimento +
                ", idProposta='" + idProposta + '\'' +
                '}';
    }

    public Cartao convertResponseToEntity() {
        return new Cartao(id, emitidoEm, titular, limite);
    }
}
