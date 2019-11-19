package com.voltaireu.vocabularist.website.model;

import com.fasterxml.jackson.annotation.*;
import com.voltaireu.vocabularist.user.model.User;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany
    @JsonIgnore
    private List<WebsiteWord> websiteWords = new ArrayList<>();

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

    @JsonProperty("userId")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<WebsiteWord> getWebsiteWords() {
        return websiteWords;
    }

    public void addWebsiteWord(WebsiteWord websiteWord) {
        websiteWords.add(websiteWord);
    }

    public void removeWebsiteWord(WebsiteWord websiteWord) {
        websiteWords.remove(websiteWord);
    }
}
