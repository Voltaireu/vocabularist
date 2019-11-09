package com.voltaireu.vocabularist.dictionary;

import com.fasterxml.jackson.annotation.JsonView;
import com.voltaireu.vocabularist.other.Views;
import com.voltaireu.vocabularist.word.UserWord;

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

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_word_id")
    private UserWord userWord;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dictionary_id")
    private Dictionary dictionary;

    public DictionaryWord() {
    }

    public DictionaryWord(int amountInSource, UserWord userWord, Dictionary dictionary) {
        this.amountInSource = amountInSource;
        this.userWord = userWord;
        this.dictionary = dictionary;
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

    public UserWord getUserWord() {
        return userWord;
    }

    public void setUserWord(UserWord userWord) {
        this.userWord = userWord;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }
}
