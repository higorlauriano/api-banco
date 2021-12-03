package com.higor.banco.model.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.higor.banco.model.entity.MovimentoConta;

import java.math.BigDecimal;
import java.util.Date;

public class MovimentoContaResponseDto {

    private String tipoEs;

    private String tipoMovimento;

    private BigDecimal valorMovimento;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date dataMovimento;

    public MovimentoContaResponseDto(MovimentoConta movimentoConta) {
        this.tipoEs = movimentoConta.getTipoES().getValue();
        this.tipoMovimento = movimentoConta.getTipoMovimento().getValue();
        this.valorMovimento = movimentoConta.getValorMovimento();
        this.dataMovimento = movimentoConta.getDataHoraMovimento();
    }

    public String getTipoEs() {
        return tipoEs;
    }

    public void setTipoEs(String tipoEs) {
        this.tipoEs = tipoEs;
    }

    public String getTipoMovimento() {
        return tipoMovimento;
    }

    public void setTipoMovimento(String tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }

    public BigDecimal getValorMovimento() {
        return valorMovimento;
    }

    public void setValorMovimento(BigDecimal valorMovimento) {
        this.valorMovimento = valorMovimento;
    }

    public Date getDataMovimento() {
        return dataMovimento;
    }

    public void setDataMovimento(Date dataMovimento) {
        this.dataMovimento = dataMovimento;
    }
}
