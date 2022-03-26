package com.efimchick.ifmo.collections.countwords;

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

    private final Map<String, Integer> mapOfWords = new TreeMap<>();
    private final WordsComparator wordsComparator = new WordsComparator();

    public String countWords(List<String> lines) {
        readToMap(lines);
        return createResultString(sortMapOfWords()).toString();
    }

    private void readToMap(List<String> lines) {
        Matcher matcher;
        for (String text : lines) {
            matcher = PATTERN_LONG_WORDS.matcher(text.toLowerCase());
            while (matcher.find()) {
                String group = matcher.group();
                mapOfWords.put(group, mapOfWords.getOrDefault(group, 0) + 1);
            }
        }
    }

    private List<Map.Entry<String, Integer>> sortMapOfWords() {
        List<Map.Entry<String, Integer>> listOfWords = new ArrayList<>(mapOfWords.entrySet());
        listOfWords.sort(wordsComparator);
        return listOfWords;
    }

    private StringBuilder createResultString(List<Map.Entry<String, Integer>> listOfWords) {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, Integer> x : listOfWords) {
            if (x.getValue() >= MIN_REPEAT_WORDS) {
                result.append(String.format(RESULT_FORMAT, x.getKey(), x.getValue()));
            }
        }
        result.deleteCharAt(result.length() - 1);
        return result;
    }
}
