package com.voltaireu.vocabularist.exam;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class ExamIsFinishedException extends RuntimeException {

    public ExamIsFinishedException(String message) {
        super(message);
    }
}
