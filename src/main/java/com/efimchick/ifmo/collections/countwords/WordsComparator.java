package com.efimchick.ifmo.collections.countwords;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;

public class WordsComparator implements Comparator<Map.Entry<String, Integer>>, Serializable {
    private static final long serialVersionUID = 1;

    @Override
    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
        return o2.getValue().compareTo(o1.getValue());
    }
}
