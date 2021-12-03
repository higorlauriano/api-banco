package com.higor.banco.exception;

import java.text.MessageFormat;

public class CustomRuntimeException extends RuntimeException {

    private String codigoErro = "-01";

    public CustomRuntimeException(String mensagem) {
        super(mensagem);
    }

    public CustomRuntimeException(ICustomException ex) {
        super(ex.getMensagem());
        this.codigoErro = ex.getCodigo();
    }

    public CustomRuntimeException(ICustomException ex, Object... args) {
        super(MessageFormat.format(ex.getMensagem(), args));
        this.codigoErro = ex.getCodigo();
    }

    public String getCodigoErro() {
        return codigoErro;
    }
}
