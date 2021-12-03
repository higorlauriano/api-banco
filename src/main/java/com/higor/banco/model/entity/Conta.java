package com.higor.banco.model.entity;

import com.higor.banco.pattern.DefaultEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

@Entity
@Table(name = "CONTA")
public class Conta extends DefaultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    @Column(name = "NUM_AGENCIA", length = 4)
    private Integer numeroAgencia;

    @Column(name = "NUM_CONTA", length = 8)
    private Integer numeroConta;

    @Column(name = "SALDO_ATUAL")
    private BigDecimal saldoAtual;

    @Column(name = "DH_CRIACAO", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraCriacao;

    @Column(name = "ATIVO")
    private boolean ativo;

    @PrePersist
    private void generateDadosConta() {
        this.setNumeroAgencia(new Random().nextInt(9999));
        this.setNumeroConta(new Random().nextInt(99999999));
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public BigDecimal getSaldoAtual() {
        return saldoAtual;
    }

    public void setSaldoAtual(BigDecimal saldoAtual) {
        this.saldoAtual = saldoAtual;
    }

    public Date getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(Date dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
