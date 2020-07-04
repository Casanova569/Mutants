package com.magneto.mutants.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class MutantServiceException extends RuntimeException {

  public MutantServiceException(final String message) {
    super(message);
  }

  public MutantServiceException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
