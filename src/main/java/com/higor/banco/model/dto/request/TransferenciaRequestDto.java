package com.higor.banco.model.dto.request;

import java.math.BigDecimal;

public class TransferenciaRequestDto {

    private BigDecimal valor;

    private TransferenciaContaDto contaPartida;

    private TransferenciaContaDto contaDestino;

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public TransferenciaContaDto getContaPartida() {
        return contaPartida;
    }

    public void setContaPartida(TransferenciaContaDto contaPartida) {
        this.contaPartida = contaPartida;
    }

    public TransferenciaContaDto getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(TransferenciaContaDto contaDestino) {
        this.contaDestino = contaDestino;
    }
}
