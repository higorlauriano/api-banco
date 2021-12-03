package com.higor.banco.exception.enums;

import com.higor.banco.exception.ICustomException;

public enum EnumContaException implements ICustomException {

    SALDO_INICIAL_INVALIDO("CC01", "Saldo Inicial da Conta não pode ser inferior a zero."),
    CLIENTE_NAO_INFORMADO("CC02", "Não foi informado um Cliente para a Conta"),
    DADOS_CLIENTE_NAO_INFORMADOS("CC03", "É necessário informar os dados do cliente dono da Conta"),
    CONTA_NAO_ENCONTRADA("CC04", "Não foi encontrada uma conta com os dados informados."),
    DADOS_INVALIDOS("CC05", "Dados inválidos, verifique os valores informados."),;

    private final String codigo, mensagem;

    EnumContaException(String codigo, String mensagem) {
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
