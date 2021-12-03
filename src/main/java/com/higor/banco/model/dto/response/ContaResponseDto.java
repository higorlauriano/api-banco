package com.higor.banco.model.dto.response;

import com.higor.banco.model.entity.Conta;

public class ContaResponseDto {

    private Integer id;

    private Integer numeroAgencia;

    private Integer numeroConta;

    private ClienteResponseDto cliente;

    public ContaResponseDto(Conta conta) {
        this.id = conta.getId();
        this.numeroAgencia = conta.getNumeroAgencia();
        this.numeroConta = conta.getNumeroConta();
        this.cliente = new ClienteResponseDto(conta.getCliente());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumeroAgencia() {
        return numeroAgencia;
    }

    public void setNumeroAgencia(Integer numeroAgencia) {
        this.numeroAgencia = numeroAgencia;
    }

    public Integer getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(Integer numeroConta) {
        this.numeroConta = numeroConta;
    }

    public ClienteResponseDto getCliente() {
        return cliente;
    }

    public void setCliente(ClienteResponseDto cliente) {
        this.cliente = cliente;
    }

}
