package com.magneto.mutants.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class MutantDNAExepction extends RuntimeException {

  public MutantDNAExepction(final String message) {
    super(message);
  }

  public MutantDNAExepction(final String message, final Throwable exception) {
    super(message, exception);
  }
}
