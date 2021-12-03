package com.higor.banco.model.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.higor.banco.model.entity.Conta;
import com.higor.banco.util.DateUtil;

import java.math.BigDecimal;
import java.util.Date;

public class ConsultaSaldoResponseDto {

    private BigDecimal saldoAtual;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date dataSaldo;

    public ConsultaSaldoResponseDto(final Conta conta) {
        this.saldoAtual = conta.getSaldoAtual();
        this.dataSaldo = DateUtil.getDataHoraAtual();
    }

    public BigDecimal getSaldoAtual() {
        return saldoAtual;
    }

    public void setSaldoAtual(BigDecimal saldoAtual) {
        this.saldoAtual = saldoAtual;
    }
}
