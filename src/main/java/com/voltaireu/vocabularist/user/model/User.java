package com.voltaireu.vocabularist.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.voltaireu.vocabularist.other.Views;
import com.voltaireu.vocabularist.security.authority.Role;
import com.voltaireu.vocabularist.website.model.Website;
import com.voltaireu.vocabularist.word.model.UserWord;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    @JsonView(Views.Public.class)
    private Long id;

    @NotNull
    @NotEmpty
    @Column(name = "user_username", unique = true)
    @JsonView(Views.Public.class)
    private String username;

    @NotNull
    @NotEmpty
    @Column(name = "user_password")
    @JsonView(Views.Internal.class)
    private String password;

    @Email
    @NotNull
    @NotEmpty
    @Column(name = "user_email", unique = true)
    @JsonView(Views.Public.class)
    private String email;

    @Column(name = "user_enabled")
    @JsonView(Views.Internal.class)
    private boolean enabled;

    @OneToMany
    private List<Website> websites = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    private List<UserWord> userWords = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "role_id")}
    )
    private List<Role> roles = new LinkedList<>();

    public User(
            String username,
            String password,
            String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = false;
    }

    public User() {
        this.enabled = false;
    }


    public List<UserWord> getUserWords() {
        return userWords;
    }

    public void addUserWord(UserWord userWord) {
        userWords.add(userWord);
    }

    public List<Website> getWebsites() {
        return websites;
    }

    public void addWebsite(Website website) {
        websites.add(website);
    }

    public void removeWebsite(Website website) {
        websites.remove(website);
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
