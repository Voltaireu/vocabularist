package com.voltaireu.vocabularist.security.jwt;

import java.io.Serializable;

public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;

    private final String jwt;

    public JwtAuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return this.jwt;
    }
}