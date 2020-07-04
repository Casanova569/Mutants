package com.magneto.mutants.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MutantDnaException extends RuntimeException {

  public MutantDnaException(final String message) {
    super(message);
  }
}
