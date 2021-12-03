package com.higor.banco.model.entity;

import com.higor.banco.pattern.DefaultEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TRANSACAO")
public class Transacao extends DefaultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @JoinColumn(name = "ID_CONTA_ORIGEM", referencedColumnName = "ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Conta contaOrigem;

    @JoinColumn(name = "ID_CONTA_DESTINO", referencedColumnName = "ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Conta contaDestino;

    @Column(name = "PROTOCOLO")
    private String protocolo;

    @Column(name = "DH_TRANSACAO")
    private Date dataHoraTransacao;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Conta getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(Conta contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public Conta getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(Conta contaDestino) {
        this.contaDestino = contaDestino;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public Date getDataHoraTransacao() {
        return dataHoraTransacao;
    }

    public void setDataHoraTransacao(Date dataHoraTransacao) {
        this.dataHoraTransacao = dataHoraTransacao;
    }
}
