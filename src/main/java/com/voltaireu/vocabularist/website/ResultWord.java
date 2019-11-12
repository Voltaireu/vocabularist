package com.voltaireu.vocabularist.website;

public class ResultWord {

    private String text;
    private int amount;

    ResultWord(String text, int amount) {
        this.text = text;
        this.amount = amount;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
