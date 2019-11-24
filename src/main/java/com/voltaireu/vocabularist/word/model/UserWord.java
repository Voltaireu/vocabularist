package com.voltaireu.vocabularist.word.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.voltaireu.vocabularist.other.Views;
import com.voltaireu.vocabularist.user.model.User;

import javax.persistence.*;

@Entity
@Table(name = "user_word")
public class UserWord {

    @Id
    @GeneratedValue
    @Column(name = "user_word_id")
    @JsonView(Views.Public.class)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_word_word_id")
    private Word word;

    @Column(name = "user_word_amount")
    private int amountInGeneral;

    @ManyToOne
    @JoinColumn(name = "user_word_user_id")
    private User user;

    @Column(name = "user_word_known")
    private boolean known;

    public UserWord() {
        this.amountInGeneral = 0;
    }

    public UserWord(int amountInGeneral) {
        this.amountInGeneral = amountInGeneral;
    }

    public Long getId() {
        return id;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public int getAmountInGeneral() {
        return amountInGeneral;
    }

    public void setAmountInGeneral(int amountInGeneral) {
        this.amountInGeneral = amountInGeneral;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isKnown() {
        return known;
    }

    public void setKnown(boolean known) {
        this.known = known;
    }
}
