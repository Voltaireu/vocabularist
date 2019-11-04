package com.voltaireu.vocabularist.security;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
public class TimeProvider implements Serializable {

    private static final long serialVersionUID = 6757894822288844844L;

    public Date now(){
        return new Date();
    }
}
