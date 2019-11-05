package com.voltaireu.vocabularist.word;

import com.fasterxml.jackson.annotation.JsonView;
import com.voltaireu.vocabularist.language.LanguageName;
import com.voltaireu.vocabularist.other.Views;

import javax.persistence.*;

@Entity
@Table(name = "word")
public class Word {

    @Id
    @GeneratedValue
    @Column(name = "word_id")
    @JsonView(Views.Public.class)
    private Long id;

    @Column(name = "word_text")
    private String text;

    @Column(name = "word_language")
    @Enumerated(EnumType.STRING)
    private LanguageName language;

    @Column(name = "word_part_of_speech")
    @Enumerated(EnumType.STRING)
    private PartOfSpeechName partOfSpeech;

    public Word() {
    }

    public Word(String text, LanguageName language, PartOfSpeechName partOfSpeech) {
        this.text = text;
        this.language = language;
        this.partOfSpeech = partOfSpeech;
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

    public LanguageName getLanguage() {
        return language;
    }

    public void setLanguage(LanguageName language) {
        this.language = language;
    }

    public PartOfSpeechName getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(PartOfSpeechName partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }
}
