package com.voltaireu.vocabularist.exam.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.voltaireu.vocabularist.other.Views;
import com.voltaireu.vocabularist.website.model.Website;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "exam")
public class Exam {

    @Id
    @GeneratedValue
    @Column(name = "exam_id")
    @JsonView(Views.Public.class)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exam_website")
    private Website website;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Flashcard> flashcards = new ArrayList<>();

    @Column(name = "exam_finished")
    private boolean finished;

    public Exam() {
        this.finished = false;
    }

    public Long getId() {
        return id;
    }

    public List<Flashcard> getFlashcards() {
        return flashcards;
    }

    public void addFlashcard(Flashcard flashcard) {
        this.flashcards.add(flashcard);
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public Website getWebsite() {
        return website;
    }

    public void setWebsite(Website website) {
        this.website = website;
    }
}
