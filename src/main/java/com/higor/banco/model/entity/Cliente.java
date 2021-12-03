package com.higor.banco.model.entity;

import com.higor.banco.pattern.DefaultEntity;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "CLIENTE")
public class Cliente extends DefaultEntity {

    @ApiModelProperty(value = "Código do Cliente")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ApiModelProperty(value = "Nome do Cliente")
    @Column(name = "NOME")
    private String nome;

    @ApiModelProperty(value = "CPF ou CNPJ do Cliente")
    @Column(name = "CPF_CNPJ")
    private String cpfCnpj;

    @ApiModelProperty(value = "Se o cliente está ativo")
    @Column(name = "ATIVO")
    private boolean ativo;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
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
