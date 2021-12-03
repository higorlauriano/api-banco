package com.higor.banco.model.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ConsultaExtratoResponse {

    private List<MovimentoContaResponseDto> movimentacoes;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date dataInicio;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date dataFim;

    private BigDecimal saldoInicial;

    private BigDecimal saldoFinal;

    public List<MovimentoContaResponseDto> getMovimentacoes() {
        return movimentacoes;
    }

    public void setMovimentacoes(List<MovimentoContaResponseDto> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public BigDecimal getSaldoFinal() {
        return saldoFinal;
    }

    public void setSaldoFinal(BigDecimal saldoFinal) {
        this.saldoFinal = saldoFinal;
    }
}
