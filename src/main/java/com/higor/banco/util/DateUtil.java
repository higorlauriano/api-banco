package com.higor.banco.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    public static final Locale LOCALE = new Locale("pt", "BR");

    public static Date getDataHoraAtual() {
        return Calendar.getInstance().getTime();
    }

    public static Calendar zerarHora(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    public static Calendar zerarHora(Date data) {
        Calendar c1 = Calendar.getInstance(LOCALE);
        c1.setTime(data);
        return zerarHora(c1);
    }

    public static Date maxHour(Date data) {
        if (data == null) {
            return null;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.set(Calendar.HOUR_OF_DAY, cal.getActualMaximum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getActualMaximum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getActualMaximum(Calendar.MILLISECOND));

        return cal.getTime();
    }

    public static Date getDataAtual() {
        return zerarHora(getDataHoraAtual()).getTime();
    }

}
