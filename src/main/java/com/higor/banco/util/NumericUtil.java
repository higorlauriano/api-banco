package com.higor.banco.util;

public class NumericUtil {

    public synchronized static boolean isGreater(Number number, Number compareTo) {
        return number != null && compareTo != null && number.doubleValue() > compareTo.doubleValue();
    }

    public synchronized static boolean isGreaterThanZero(Number number) {
        return isGreater(number, 0);
    }

    public synchronized static boolean isLess(Number number, Number compareTo) {
        return number != null && number.doubleValue() < compareTo.doubleValue();
    }

    public synchronized static boolean isLessOrEquals(Number number, Number compareTo) {
        return number != null && number.doubleValue() <= compareTo.doubleValue();
    }

    public synchronized static boolean isLessOrEqualsZero(Number number) {
        return isLessOrEquals(number, 0);
    }

    public synchronized static boolean isLessThanZero(Number number) {
        return isLess(number, 0);
    }

}
