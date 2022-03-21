package com.efimchick.ifmo.collections.countwords;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Words {
    public static final String RESULT_FORMAT = "%s - %d\n";
    public static final int MIN_REPEAT_WORDS = 10;
    private static final Pattern PATTERN_LONG_WORDS = Pattern.compile("\\p{IsAlphabetic}{4,}");

    private final StringBuilder result = new StringBuilder();
    private final Map<String, Integer> mapOfWords = new TreeMap<>();

    public String countWords(Iterable<String> lines) {

        Matcher matcher;
        for (String text : lines) {
            matcher = PATTERN_LONG_WORDS.matcher(text.toLowerCase());
            while (matcher.find()) {
                mapOfWords.put(matcher.group(), mapOfWords.getOrDefault(matcher.group(), 0) + 1);
            }
        }

        List<Map.Entry<String,Integer>> listOfWords = new ArrayList<>(mapOfWords.entrySet());
        listOfWords.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        for (Map.Entry<String,Integer> x : listOfWords) {
            if (x.getValue() >= MIN_REPEAT_WORDS) {
                result.append(String.format(RESULT_FORMAT, x.getKey(), x.getValue()));
            }
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }
}
