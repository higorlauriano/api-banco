package com.higor.banco.util;

import org.springframework.util.StringUtils;

public class StringUtil extends StringUtils {

    public static final String somenteNumeros(String str) {
        if (!hasLength(str)) {
            return str;
        }

        return str.replaceAll("[^0-9]+", "");
    }

}
