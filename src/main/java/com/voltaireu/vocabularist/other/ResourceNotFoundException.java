package com.voltaireu.vocabularist.other;

import com.voltaireu.vocabularist.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 2067398214076340822L;

    public ResourceNotFoundException(Class resourceClass, String parameterName, Object parameter) {
        super("No " + resourceClass.getSimpleName() + " with " + parameterName + parameter + " found!");
    }

    public ResourceNotFoundException(Class resourceClass, long id) {
        super("No " + resourceClass.getSimpleName() + " with id " + id + " found!");
    }
}
