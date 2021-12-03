package com.higor.banco.model.dto.response;

import com.higor.banco.model.entity.Cliente;

public class ClienteResponseDto {

    private Integer id;

    private String nome;

    private String cpfCnpj;

    private boolean ativo;

    public ClienteResponseDto(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.cpfCnpj = cliente.getCpfCnpj();
        this.ativo = cliente.isAtivo();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
