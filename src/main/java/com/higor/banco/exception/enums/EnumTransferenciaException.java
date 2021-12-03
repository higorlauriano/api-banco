package com.higor.banco.exception.enums;

import com.higor.banco.exception.ICustomException;

public enum EnumTransferenciaException implements ICustomException {

    VALOR_INVALIDO("T01", "Valor informado para a transferência é inválido."),
    DADOS_CONTA_PARTIDA_INVALIDOS("T02", "Dados da conta de partida inválidos."),
    DADOS_CONTA_DESTINO_INVALIDOS("T03", "Dados da conta de destino inválidos."),
    CONTA_PARTIDA_NAO_ENCONTRADA("T04", "Conta de partida da transferência não encontrada."),
    CONTA_DESTINO_NAO_ENCONTRADA("T05", "Conta de destino da transferência não encontrada."),
    CONTAS_PARTIDA_DESTINO_IGUAIS("T06", "Não pode ser realizada uma transferência para a mesma conta de origem/destino."),
    DADOS_INVALIDOS("T07", "Dados inválidos, verifique os valores informados."),
    SALDO_INSUFICIENTE("T08", "Saldo atual da conta de partida não é suficiente para efetuar a transação."),;

    private final String codigo, mensagem;

    EnumTransferenciaException(String codigo, String mensagem) {
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
