package com.voltaireu.vocabularist.website;

import com.fasterxml.jackson.annotation.*;
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

    @OneToOne
    @JoinColumn(name = "website_dictionary_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Dictionary dictionary;

    @ManyToOne
    @JoinColumn(name = "website_user_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private User user;

    public Website() {
    }

    public Website(String url) {
        this.url = url;
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
