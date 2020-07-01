package com.magneto.mutants.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class MutantStatsException extends RuntimeException {

    public MutantStatsException(final String message) {
        super(message);
    }
}
