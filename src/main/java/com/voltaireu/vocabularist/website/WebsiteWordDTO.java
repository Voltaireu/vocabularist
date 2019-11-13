package com.voltaireu.vocabularist.website;

public class WebsiteWordDTO {
    private Long wordId;
    private String wordText;
    private int amountOnWebsite;

    public Long getWordId() {
        return wordId;
    }

    public void setWordId(Long wordId) {
        this.wordId = wordId;
    }

    public String getWordText() {
        return wordText;
    }

    public void setWordText(String wordText) {
        this.wordText = wordText;
    }

    public int getAmountOnWebsite() {
        return amountOnWebsite;
    }

    public void setAmountOnWebsite(int amountOnWebsite) {
        this.amountOnWebsite = amountOnWebsite;
    }
}
