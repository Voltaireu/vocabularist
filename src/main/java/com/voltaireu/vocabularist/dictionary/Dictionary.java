package com.voltaireu.vocabularist.dictionary;

import com.fasterxml.jackson.annotation.JsonView;
import com.voltaireu.vocabularist.other.Views;
import com.voltaireu.vocabularist.website.Website;
import com.voltaireu.vocabularist.word.DictionaryWord;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dictionary")
public class Dictionary {

    @Id
    @GeneratedValue
    @Column(name = "dictionary_id")
    private Long id;

    @OneToOne(mappedBy = "dictionary")
    @JoinColumn(name = "website_id")
    private Website website;

    @OneToMany(mappedBy = "dictionary")
    private List<DictionaryWord> dictionaryWords = new ArrayList<>();

    public Dictionary() {
    }

    public Dictionary(Website website, List<DictionaryWord> dictionaryWords) {
        this.website = website;
        this.dictionaryWords = dictionaryWords;
    }

    public Long getId() {
        return id;
    }

    public Website getWebsite() {
        return website;
    }

    public void setWebsite(Website website) {
        this.website = website;
    }

    public List<DictionaryWord> getDictionaryWords() {
        return dictionaryWords;
    }

    public void setDictionaryWords(List<DictionaryWord> dictionaryWords) {
        this.dictionaryWords = dictionaryWords;
    }
}
