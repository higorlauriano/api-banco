package com.higor.banco.model.entity;

import com.higor.banco.model.enums.EnumTipoES;
import com.higor.banco.model.enums.EnumTipoMovimento;
import com.higor.banco.pattern.DefaultEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "MOVIMENTO_CONTA")
public class MovimentoConta extends DefaultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @JoinColumn(name = "ID_CONTA", referencedColumnName = "ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Conta conta;

    @Column(name = "TIPO_ES")
    @Enumerated(EnumType.STRING)
    private EnumTipoES tipoES;

    @Column(name = "TIPO_MOVIMENTO")
    @Enumerated(EnumType.STRING)
    private EnumTipoMovimento tipoMovimento;

    @Column(name = "VALOR_MOVIMENTO")
    private BigDecimal valorMovimento;

    @Column(name = "SALDO_CONTA")
    private BigDecimal saldoConta = BigDecimal.ZERO;

    @Column(name = "DH_MOVIMENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraMovimento;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public EnumTipoES getTipoES() {
        return tipoES;
    }

    public void setTipoES(EnumTipoES tipoES) {
        this.tipoES = tipoES;
    }

    public EnumTipoMovimento getTipoMovimento() {
        return tipoMovimento;
    }

    public void setTipoMovimento(EnumTipoMovimento tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }

    public BigDecimal getValorMovimento() {
        return valorMovimento;
    }

    public void setValorMovimento(BigDecimal valorMovimento) {
        this.valorMovimento = valorMovimento;
    }

    public BigDecimal getSaldoConta() {
        return saldoConta;
    }

    public void setSaldoConta(BigDecimal saldoConta) {
        this.saldoConta = saldoConta;
    }

    public Date getDataHoraMovimento() {
        return dataHoraMovimento;
    }

    public void setDataHoraMovimento(Date dataHoraMovimento) {
        this.dataHoraMovimento = dataHoraMovimento;
    }
}
