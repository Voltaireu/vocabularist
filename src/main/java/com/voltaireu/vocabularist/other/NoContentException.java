package com.voltaireu.vocabularist.other;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoContentException extends RuntimeException{

    private static final long serialVersionUID = -2122010907511386096L;

    public NoContentException() {
    }
}
