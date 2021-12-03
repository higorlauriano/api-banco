package com.higor.banco.exception.enums;

import com.higor.banco.exception.ICustomException;

public enum EnumClienteException implements ICustomException {

    NOME_NAO_INFORMADO("C01", "Nome do Cliente não foi informado"),
    NOME_INVALIDO("C02", "Nome do Cliente inválido, permitido entre 2 e 100 caracteres."),
    CPF_CNPJ_NAO_INFORMADO("C03", "CPF/CNPJ do Cliente não foi informado"),
    CPF_CNPJ_INVALIDO("C04", "CPF/CNPJ informado não é válido."),
    CLIENTE_NAO_ENCONTRADO("C05", "Não foi encontrado um cliente com os dados informados"),
    CLIENTE_JA_CADASTRADO("C06", "Já existe um Cliente cadastrado com os dados informados."),
    DADOS_INVALIDOS("C07", "Dados inválidos, verifique os valores informados.");

    private final String codigo, mensagem;

    EnumClienteException(String codigo, String mensagem) {
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
