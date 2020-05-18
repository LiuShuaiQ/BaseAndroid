package com.lem.common_tool;

import java.util.Collection;
import java.util.Map;

/**
 * author: liushuai.
 * date:   18-9-14 下午6:27
 * mail: 1462828919@qq.com
 */
public class CollectionUtil {
    public static boolean isEmpty(Map map) {
        return map == null || map.size() <= 0 || map.isEmpty();
    }

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.size() <= 0;
    }

    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length <= 0;
    }

    public static <T extends Number> double sumNum(Collection<T> list) {
        double sum = 0;
        for (T v : list) {
            sum += v.doubleValue();
        }
        return sum;
    }

    public static <T extends Number> float sumNumF(Collection<T> list) {
        float sum = 0;
        for (T v : list) {
            sum += v.doubleValue();
        }
        return sum;
    }
}
