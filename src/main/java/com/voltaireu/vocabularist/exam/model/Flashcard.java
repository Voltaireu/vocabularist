package com.voltaireu.vocabularist.exam.model;

import com.fasterxml.jackson.annotation.*;
import com.voltaireu.vocabularist.other.Views;
import com.voltaireu.vocabularist.word.model.Word;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "flashcard")
public class Flashcard {

    @Id
    @GeneratedValue
    @Column(name = "flashcard_id")
    @JsonView(Views.Public.class)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "flashcard_exam_id")
    @JsonBackReference
    private Exam exam;

    @ManyToOne(optional = false)
    @JoinColumn(name = "flashcard_word_id")
    private Word word;

    @NotNull
    @Column(name = "flashcard_known")
    private boolean known;

    public Flashcard() {
    }

    public Long getId() {
        return id;
    }

    public boolean isKnown() {
        return known;
    }

    public void setKnown(boolean known) {
        this.known = known;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
}
