package com.voltaireu.vocabularist.exam;

import com.voltaireu.vocabularist.website.model.WebsiteWord;
import com.voltaireu.vocabularist.word.model.Word;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class DrawingUtil {

    /**
     * Draws random word from list, probability of drawing given word equals amountOfWord / (sum of amounts from all
     * WebsiteWord objects in the list)
     * @param websiteWords list of WebsiteWordObjects
     * @return Word object extracted from drawn WebsiteWord object from the list
     */
    public Word drawWord(List<WebsiteWord> websiteWords) {
        Map<Integer, Word> words = new LinkedHashMap<>();
        Map<Integer, Integer> prefix = new LinkedHashMap<>();
        int counter = 0, sumOfAmounts = 0;
        for (WebsiteWord websiteWord : websiteWords) {
            int amount = websiteWord.getAmountOnWebsite();
            sumOfAmounts += amount;
            words.put(counter, websiteWord.getWord());
            prefix.put(counter, sumOfAmounts);
            counter++;
        }


        int random = getRandomFromScope(1, sumOfAmounts);
        int drawnWordIndex = findCeil(prefix, random,0, websiteWords.size() - 1);
        return words.get(drawnWordIndex);
    }

    private int getRandomFromScope(int min, int max) {
        return (int) (Math.random() * ((max - min))) + 1;
    }

    private int findCeil(Map<Integer, Integer> map, int number, int bottomIndex, int topIndex) {
        int middleIndex;
        while (bottomIndex < topIndex) {
            middleIndex = bottomIndex + ((topIndex - bottomIndex) / 2);
            if (number > map.get(middleIndex)) {
                bottomIndex = middleIndex + 1;
            } else {
                topIndex = middleIndex;
            }
        }
        return bottomIndex;
    }

}