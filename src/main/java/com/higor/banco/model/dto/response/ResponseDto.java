package com.higor.banco.model.dto.response;

public class ResponseDto {

    private String mensagem;

    public ResponseDto(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
