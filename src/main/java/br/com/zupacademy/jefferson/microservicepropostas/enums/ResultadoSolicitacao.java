package br.com.zupacademy.jefferson.microservicepropostas.enums;

public enum ResultadoSolicitacao {

    COM_RESTRICAO{
        public StatusProposta getStatusResposta() {
            return StatusProposta.NAO_ELEGIVEL;
        }
    },
    SEM_RESTRICAO{
        public StatusProposta getStatusResposta() {
            return StatusProposta.ELEGIVEL;
        }
    };

    public abstract StatusProposta getStatusResposta();
}
