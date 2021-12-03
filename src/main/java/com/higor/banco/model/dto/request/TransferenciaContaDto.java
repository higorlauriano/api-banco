package com.higor.banco.model.dto.request;

public class TransferenciaContaDto {

    private Integer idConta;

    private Integer numeroAgencia;

    private Integer numeroConta;

    private BeneficiarioDto beneficiario;

    public Integer getIdConta() {
        return idConta;
    }

    public void setIdConta(Integer idConta) {
        this.idConta = idConta;
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

    public BeneficiarioDto getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(BeneficiarioDto beneficiario) {
        this.beneficiario = beneficiario;
    }
}
