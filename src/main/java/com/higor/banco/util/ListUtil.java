package com.higor.banco.util;

import java.util.Collection;
import java.util.stream.Stream;

public class ListUtil {

    public synchronized static <T> T first(Collection<T> collection) {
        if (isNotNullOrEmpty(collection)) {
            return collection.iterator().next();
        }

        return null;
    }

    public synchronized static boolean isNullOrEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public synchronized static boolean isNotNullOrEmpty(Collection collection) {
        return !isNullOrEmpty(collection);
    }

    public static <T> Stream<T> stream(Collection<T> collection) {
        return isNullOrEmpty(collection)
                ? Stream.empty()
                : collection.stream();
    }

}
