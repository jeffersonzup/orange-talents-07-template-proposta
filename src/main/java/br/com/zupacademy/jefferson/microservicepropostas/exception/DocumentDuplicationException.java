package br.com.zupacademy.jefferson.microservicepropostas.exception;

public class DocumentDuplicationException extends RuntimeException {

    public DocumentDuplicationException(String msg) {
        super(msg);
    }
}
