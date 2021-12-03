package com.higor.banco.model.dto.request;

import java.math.BigDecimal;

public class ContaRequestDto {

    private BigDecimal saldoInicial;

    private ContaClienteRequestDto cliente;

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public ContaClienteRequestDto getCliente() {
        return cliente;
    }

    public void setCliente(ContaClienteRequestDto cliente) {
        this.cliente = cliente;
    }
}
