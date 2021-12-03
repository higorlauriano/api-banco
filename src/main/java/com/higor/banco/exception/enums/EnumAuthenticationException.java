package com.higor.banco.exception.enums;

import com.higor.banco.exception.ICustomException;

public enum EnumAuthenticationException implements ICustomException {

    USUARIO_NAO_ENCONTRADO("A01", "Usuario não encontrado"),
    TOKEN_NAO_INFORMADO("A02", "Token não informado"),
    TOKEN_EXPIRADO("A03", "Token expirado"),
    DADOS_INVALIDOS("A04", "Dados para geração do Token inválidos, verifique os campos informados"),;

    private final String codigo, mensagem;

    EnumAuthenticationException(String codigo, String mensagem) {
        this.codigo = codigo;
        this.mensagem = mensagem;
    }

    @Override
    public String getCodigo() {
        return codigo;
    }

    @Override
    public String getMensagem() {
        return mensagem;
    }

}
