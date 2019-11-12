package com.voltaireu.vocabularist.website;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

@Service
public class TextAnalysisService {

    List<WordAmountDTO> countWords(String text) {
        List<String> words = extractWords(text);
        Map<String, Integer> countedWords = countWords(words);
        Map<String, Integer> sortedCountedWords = sortByAmountDescending(countedWords);

        List<WordAmountDTO> resultWords = new LinkedList<>();
        sortedCountedWords.forEach((word,amount)-> resultWords.add(new WordAmountDTO(word, amount)));
        return resultWords;
    }

    //TODO Add more advanced mechanism extracting phrasal verbs from text
    private List<String> extractWords(String text) {
        String onlyWordsString = text.toLowerCase().replaceAll("[^a-zA-Z]", " ");
        return Arrays.asList(onlyWordsString.split("\\s+"));
    }

    private Map<String, Integer> countWords(List<String> words) {
        Map<String, Integer> countedWords = new HashMap<>();
        for (String word : words) {
            Integer count = countedWords.get(word);
            countedWords.put(word, (count == null) ? 1 : count + 1);
        }
        return countedWords;
    }

    private LinkedHashMap<String, Integer> sortByAmountDescending(Map<String, Integer> countedWords) {
        return countedWords
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(
                        toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e2,
                                LinkedHashMap::new));
    }

    private List<String> removeDuplicates(List<String> words) {
        return words.stream().distinct().collect(Collectors.toList());
    }
}
