package com.higor.banco.validador;

import com.higor.banco.exception.CustomRuntimeException;
import com.higor.banco.exception.enums.EnumTransferenciaException;
import com.higor.banco.model.dto.request.TransferenciaContaDto;
import com.higor.banco.model.dto.request.TransferenciaRequestDto;
import com.higor.banco.util.NumericUtil;

import java.math.BigDecimal;

public class ValidadorTransferencia {

    public void validarDadosTransferencia(final TransferenciaRequestDto transferenciaRequestDto) {
        if (transferenciaRequestDto == null) {
            throw new CustomRuntimeException(EnumTransferenciaException.DADOS_INVALIDOS);
        }

        validarValorTransferencia(transferenciaRequestDto);
        validarConta(transferenciaRequestDto.getContaDestino(), false);
        validarConta(transferenciaRequestDto.getContaPartida(), true);
    }

    public void validarValorTransferencia(final TransferenciaRequestDto transferenciaRequestDto) {
        if (transferenciaRequestDto.getValor() == null
                || NumericUtil.isLessOrEqualsZero(transferenciaRequestDto.getValor())) {
            throw new CustomRuntimeException(EnumTransferenciaException.VALOR_INVALIDO);
        }
    }

    public void validarSaldoConta(final BigDecimal valorTransferencia, final BigDecimal saldoAtualConta) {
        if (NumericUtil.isGreater(valorTransferencia, saldoAtualConta)) {
            throw new CustomRuntimeException(EnumTransferenciaException.SALDO_INSUFICIENTE);
        }
    }

    public void validarConta(final TransferenciaContaDto transferenciaContaDto, final boolean isPartida) {
        if (transferenciaContaDto.getNumeroAgencia() == null
                || transferenciaContaDto.getNumeroConta() == null) {
            throw new CustomRuntimeException(
                    isPartida ? EnumTransferenciaException.DADOS_CONTA_PARTIDA_INVALIDOS
                            : EnumTransferenciaException.DADOS_CONTA_DESTINO_INVALIDOS
            );
        }

        if (String.valueOf(transferenciaContaDto.getNumeroAgencia()).length() != 4
                || String.valueOf(transferenciaContaDto.getNumeroConta()).length() != 8) {
            throw new CustomRuntimeException(
                    isPartida ? EnumTransferenciaException.DADOS_CONTA_PARTIDA_INVALIDOS
                            : EnumTransferenciaException.DADOS_CONTA_DESTINO_INVALIDOS
            );
        }
    }

}
