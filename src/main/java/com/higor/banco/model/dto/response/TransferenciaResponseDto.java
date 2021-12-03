package com.higor.banco.model.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.higor.banco.model.entity.Transacao;

import java.util.Date;

public class TransferenciaResponseDto {

    private ContaResponseDto contaOrigem;

    private ContaResponseDto contaDestino;

    private String protocolo;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date dataTransacao;

    public TransferenciaResponseDto(Transacao transacao) {
        this.protocolo = transacao.getProtocolo();
        this.dataTransacao = transacao.getDataHoraTransacao();
        this.contaOrigem = new ContaResponseDto(transacao.getContaOrigem());
        this.contaDestino = new ContaResponseDto(transacao.getContaDestino());
    }

    public ContaResponseDto getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(ContaResponseDto contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public ContaResponseDto getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(ContaResponseDto contaDestino) {
        this.contaDestino = contaDestino;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public Date getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(Date dataTransacao) {
        this.dataTransacao = dataTransacao;
    }
}
