package com.voltaireu.vocabularist.security.model;

import com.voltaireu.vocabularist.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleName name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;

    public void addUser(User user){
        this.users.add(user);
    }

    public Long getId() {
        return id;
    }

    public RoleName getName(){
        return name;
    }
}
