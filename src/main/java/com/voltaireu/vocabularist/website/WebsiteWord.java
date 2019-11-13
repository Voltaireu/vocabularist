package com.voltaireu.vocabularist.website;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.voltaireu.vocabularist.other.Views;
import com.voltaireu.vocabularist.word.Word;

import javax.persistence.*;

@Entity
@Table(name = "website_word")
public class WebsiteWord {

    @Id
    @GeneratedValue
    @Column(name = "website_word_id")
    private Long id;

    @Column(name = "website_word_amount")
    private int amountOnWebsite;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "website_id")
    private Website website;

    @ManyToOne
    @JoinColumn(name = "word_id")
    private Word word;

    public WebsiteWord() {
    }

    public WebsiteWord(int amountOnWebsite, Word word) {
        this.amountOnWebsite = amountOnWebsite;
        this.word = word;
    }

    public Long getId() {
        return id;
    }

    public int getAmountOnWebsite() {
        return amountOnWebsite;
    }

    public void setAmountOnWebsite(int amountOnWebsite) {
        this.amountOnWebsite = amountOnWebsite;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public Website getWebsite() {
        return website;
    }

    public void setWebsite(Website website) {
        this.website = website;
    }
}
