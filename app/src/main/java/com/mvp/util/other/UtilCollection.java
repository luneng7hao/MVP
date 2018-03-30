package com.mvp.util.other;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Asion on 2018/1/16.
 * 集合判断工具
 * Collection是最基本的集合接口，一个Collection代表一组Object的集合
 */

public class UtilCollection {

    /**
     * 判断是否为空
     *
     * @param collection 集合
     * @return 空为true, 否则为false
     */
    public static <E> boolean isEmpty(Collection<E> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 判断是否为空
     *
     * @param map map
     * @return 空为true, 否则为false
     */
    public static <K, V> boolean isEmpty(Map<K, V> map) {
        return map == null || map.isEmpty();
    }

    /**
     * 判断是否包含字符串
     *
     * @param arrs 数组
     * @param val  是否包含的字符串
     * @return 包含返回true, 否则为false
     */
    public static boolean isContains(String[] arrs, String val) {
        if (arrs == null || UtilString.isNull(val)) {
            return false;
        }
        for (String arrVal : arrs) {
            if (val.equals(arrVal)) {
                return true;
            }
        }
        return false;
    }
}

