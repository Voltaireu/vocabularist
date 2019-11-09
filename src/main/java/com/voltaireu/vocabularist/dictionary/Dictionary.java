package com.voltaireu.vocabularist.dictionary;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.voltaireu.vocabularist.website.Website;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "website_id")
    @JsonBackReference
    private Website website;

    @OneToMany
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

    public void addDictionaryWord(DictionaryWord dictionaryWord) {
        this.dictionaryWords.add(dictionaryWord);
    }

    public void removeDictionaryWord(DictionaryWord dictionaryWord) {
        this.dictionaryWords.remove(dictionaryWord);
    }
}
