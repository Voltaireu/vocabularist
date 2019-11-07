package com.voltaireu.vocabularist.website;

import com.voltaireu.vocabularist.word.Word;

public class WordCount {

    private String word;
    private int amount;

    WordCount(String word, int amount) {
        this.word = word;
        this.amount = amount;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
