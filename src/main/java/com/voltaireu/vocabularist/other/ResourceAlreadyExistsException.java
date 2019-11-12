package com.voltaireu.vocabularist.other;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceAlreadyExistsException extends RuntimeException{

    private static final long serialVersionUID = -4710701615903736960L;

    public ResourceAlreadyExistsException(Class resourceClass, String parameterName, Object parameter) {
        super(resourceClass.getSimpleName() + " with " + parameterName + " " + parameter + " already exists!");
    }

    public ResourceAlreadyExistsException(Class resourceClass, long id) {
        super( resourceClass.getSimpleName() + " with id " + id + " already exists!");
    }

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}
