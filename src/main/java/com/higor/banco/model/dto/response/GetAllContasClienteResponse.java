package com.higor.banco.model.dto.response;

import com.higor.banco.model.entity.Conta;
import com.higor.banco.util.ListUtil;

import java.util.ArrayList;
import java.util.List;

public class GetAllContasClienteResponse {

    private ClienteResponseDto cliente;

    private List<ContaResponseDto> contas;

    public GetAllContasClienteResponse(List<Conta> contas) {
        this.cliente = ListUtil.first(contas) != null ? new ClienteResponseDto(ListUtil.first(contas).getCliente()) : null;
        this.contas = new ArrayList<>();
        for (Conta conta : contas) {
            this.contas.add(new ContaResponseDto(conta));
        }
    }

    public ClienteResponseDto getCliente() {
        return cliente;
    }

    public void setCliente(ClienteResponseDto cliente) {
        this.cliente = cliente;
    }

    public List<ContaResponseDto> getContas() {
        return contas;
    }

    public void setContas(List<ContaResponseDto> contas) {
        this.contas = contas;
    }
}
