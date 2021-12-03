package com.higor.banco.model.enums;

public enum EnumTipoMovimento {

    TRANSFERENCIA("TR", "Transferência"),
    DEPOSITO("DP", "Depósito"),
    SAQUE("SQ", "Saque"),;

    private final String key, value;

    EnumTipoMovimento(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
