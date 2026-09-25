package org.example;

public class NomeInvalidoException extends RuntimeException {
    private String mensagem;

    public NomeInvalidoException(String s) {
        this.mensagem = s;
    }

    public String getMensagem() {
        return mensagem;
    }
}
