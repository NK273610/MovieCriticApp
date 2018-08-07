package com.dalhousie.moviecritic.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HashMapSorting {

    public static Map<String, Float> sortByComparator(
            Map<String, Float> unsortMap) {

        List<Entry<String, Float>> list = new LinkedList<Entry<String, Float>>(
                unsortMap.entrySet());

        Collections.sort(list, new MyComparator());

        Map<String, Float> sortedMap = new LinkedHashMap<String, Float>();
        for (Entry<String, Float> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
}

class MyComparator implements Comparator<Entry<String, Float>> {

    public int compare(Entry<String, Float> o1, Entry<String, Float> o2) {
        return o2.getValue().compareTo(o1.getValue());
    }
}