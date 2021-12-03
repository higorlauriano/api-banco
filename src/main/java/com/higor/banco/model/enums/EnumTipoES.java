package com.higor.banco.model.enums;

public enum EnumTipoES {

    ENTRADA(0, "Entrada"),
    SAIDA(1, "Saída");

    private final int key;

    private final String value;

    EnumTipoES(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
