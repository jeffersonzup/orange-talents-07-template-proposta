package br.com.zupacademy.jefferson.microservicepropostas.controller.data.response;

import br.com.zupacademy.jefferson.microservicepropostas.enums.ResultadoSolicitacao;

public class ResultadoAnaliseResponse {

    private String documento;
    private String nome;
    private ResultadoSolicitacao resultadoSolicitacao;
    private String idProposta;

    public ResultadoAnaliseResponse(String documento, String nome, ResultadoSolicitacao resultadoSolicitacao, String idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.resultadoSolicitacao = resultadoSolicitacao;
        this.idProposta = idProposta;
    }

    public ResultadoSolicitacao getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

    @Override
    public String toString() {
        return "ResultadoAnaliseResponse{" +
                "documento='" + documento + '\'' +
                ", nome='" + nome + '\'' +
                ", resultadoSolicitacao=" + resultadoSolicitacao +
                ", idProposta='" + idProposta + '\'' +
                '}';
    }
}
