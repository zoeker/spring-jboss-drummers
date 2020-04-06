package com.moderndrummer.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */
public class MapUtilizer {

    public static <T> Map<T, Integer> sortByClassAndValue(final Map<T, Integer> map) {
        final List<Map.Entry<T, Integer>> list = new LinkedList<Map.Entry<T, Integer>>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<T, Integer>>() {

            @Override
            public int compare(final Map.Entry<T, Integer> m1, final Map.Entry<T, Integer> m2) {
                return (m2.getValue()).compareTo(m1.getValue());
            }
        });

        final Map<T, Integer> result = new LinkedHashMap<T, Integer>();
        for (final Map.Entry<T, Integer> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public static Map<String, Integer> sortByValue(final Map<String, Integer> map) {
        final List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

            @Override
            public int compare(final Map.Entry<String, Integer> m1, final Map.Entry<String, Integer> m2) {
                return (m2.getValue()).compareTo(m1.getValue());
            }
        });

        final Map<String, Integer> result = new LinkedHashMap<String, Integer>();
        for (final Map.Entry<String, Integer> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
