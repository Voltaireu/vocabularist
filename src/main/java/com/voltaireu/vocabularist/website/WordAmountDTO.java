package com.voltaireu.vocabularist.website;

public class WordAmountDTO {

    private String text;
    private int amount;

    public WordAmountDTO(String text, int amount) {
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
