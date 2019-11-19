package com.voltaireu.vocabularist.security.authority;

import com.fasterxml.jackson.annotation.JsonView;
import com.voltaireu.vocabularist.other.Views;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_name")
    private String name;

    public Role() { }

    public Role(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
