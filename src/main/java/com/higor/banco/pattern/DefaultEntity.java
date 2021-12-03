package com.higor.banco.pattern;

import java.io.Serializable;

public abstract class DefaultEntity implements Serializable {

    private Integer id;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
