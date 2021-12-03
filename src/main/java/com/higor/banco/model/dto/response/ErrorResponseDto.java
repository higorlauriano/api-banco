package com.higor.banco.model.dto.response;

public class ErrorResponseDto {

    private String codigoErro;

    private String mensagem;

    public ErrorResponseDto(String codigoErro, String mensagem) {
        this.codigoErro = codigoErro;
        this.mensagem = mensagem;
    }

    public String getCodigoErro() {
        return codigoErro;
    }

    public void setCodigoErro(String codigoErro) {
        this.codigoErro = codigoErro;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

}
