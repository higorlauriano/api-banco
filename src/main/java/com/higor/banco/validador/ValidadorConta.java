package com.higor.banco.validador;

import com.higor.banco.exception.CustomRuntimeException;
import com.higor.banco.exception.enums.EnumContaException;
import com.higor.banco.model.dto.request.ContaRequestDto;
import com.higor.banco.model.entity.Conta;
import com.higor.banco.util.NumericUtil;

public class ValidadorConta {

    public void validarDadosNovaConta(final ContaRequestDto contaRequestDto) {
        if (contaRequestDto == null) {
            throw new CustomRuntimeException(EnumContaException.DADOS_INVALIDOS);
        }

        if (contaRequestDto.getCliente() == null) {
            throw new CustomRuntimeException(EnumContaException.DADOS_CLIENTE_NAO_INFORMADOS);
        }
    }

    public void validarCamposNovaConta(final Conta conta) {
        validarSaldoInicial(conta);
        validarCliente(conta);
    }

    public void validarSaldoInicial(final Conta conta) {
        if (conta.getSaldoAtual() != null
                && NumericUtil.isLessThanZero(conta.getSaldoAtual())) {
            throw new CustomRuntimeException(EnumContaException.SALDO_INICIAL_INVALIDO);
        }
    }

    public void validarCliente(final Conta conta) {
        if (conta.getCliente() == null) {
            throw new CustomRuntimeException(EnumContaException.CLIENTE_NAO_INFORMADO);
        }
    }

}
