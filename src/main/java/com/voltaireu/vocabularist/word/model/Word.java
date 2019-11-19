package com.voltaireu.vocabularist.word.model;

import javax.persistence.*;

@Entity
@Table(name = "word")
public class Word {

    @Id
    @GeneratedValue
    @Column(name = "word_id")
    private Long id;

    @Column(name = "word_text", unique = true)
    private String text;

    public Word() {
    }

    public Word(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
