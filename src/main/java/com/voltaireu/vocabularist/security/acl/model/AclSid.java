package com.voltaireu.vocabularist.security.acl.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "acl_sid")
public class AclSid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Boolean principal;

    @NotNull
    @Column(name = "sid", unique = true)
    private String sid;

    public Long getId() {
        return id;
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
