package com.magneto.mutants.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NotMutantDnaForbiddenException extends RuntimeException {

    public NotMutantDnaForbiddenException(final String message) {
        super(message);
    }
}
