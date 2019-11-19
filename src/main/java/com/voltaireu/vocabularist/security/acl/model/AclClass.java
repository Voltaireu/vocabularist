package com.voltaireu.vocabularist.security.acl.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "acl_class")
public class AclClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "class", unique = true)
    private String objectClass;

    public Long getId() {
        return id;
    }

    public String getObjectClass() {
        return objectClass;
    }

    public void setObjectClass(String aClass) {
        this.objectClass = aClass;
    }
}
