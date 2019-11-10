package com.voltaireu.vocabularist.dictionary;

import com.fasterxml.jackson.annotation.JsonView;
import com.voltaireu.vocabularist.other.Views;
import com.voltaireu.vocabularist.word.Word;

import javax.persistence.*;

@Entity
@Table(name = "dictionary_word")
public class DictionaryWord {

    @Id
    @GeneratedValue
    @Column(name = "dictionary_word_id")
    @JsonView(Views.Public.class)
    private Long id;

    @Column(name = "dictionary_word_amount")
    private int amountInSource;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dictionary_id")
    private Dictionary dictionary;

    @ManyToOne
    @JoinColumn(name = "word_id")
    private Word word;

    public DictionaryWord() {
    }

    public DictionaryWord(int amountInSource, Word word) {
        this.amountInSource = amountInSource;
        this.word = word;
    }

    public Long getId() {
        return id;
    }

    public int getAmountInSource() {
        return amountInSource;
    }

    public void setAmountInSource(int amountInSource) {
        this.amountInSource = amountInSource;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }
}
