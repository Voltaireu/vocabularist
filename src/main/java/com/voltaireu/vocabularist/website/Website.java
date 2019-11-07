package com.voltaireu.vocabularist.website;

import com.fasterxml.jackson.annotation.JsonView;
import com.voltaireu.vocabularist.dictionary.Dictionary;
import com.voltaireu.vocabularist.user.User;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "website")
public class Website {

    @Id
    @GeneratedValue
    @Column(name = "website_id")
    private Long id;

    @URL
    @NotEmpty
    @Column(name = "website_url")
    private String url;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dictionary_id")
    private Dictionary dictionary;

    @ManyToOne(optional = false)
    @JoinColumn(name = "website_user")
    private User user;

    public Website() {
    }

    public Website(String url, Dictionary dictionary, User user) {
        this.url = url;
        this.dictionary = dictionary;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
