package com.voltaireu.vocabularist.word;

public class WordKnownDTO {

    private String text;
    private boolean known;

    public WordKnownDTO(String text, boolean known) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isKnown() {
        return known;
    }

    public void setKnown(boolean known) {
        this.known = known;
    }
}
