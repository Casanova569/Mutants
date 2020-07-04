package com.magneto.mutants.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class MutantStatsServiceException extends RuntimeException {

    public MutantStatsServiceException(final String message) {
        super(message);
    }

    public MutantStatsServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
