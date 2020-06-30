package com.magneto.mutants.services.mutant;

import com.google.inject.ImplementedBy;
import com.magneto.mutants.services.mutant.impl.IsValidDnaService;
import java.util.List;

@FunctionalInterface
@ImplementedBy(IsValidDnaService.class)
public interface IIsValidDnaService {

    boolean isValid(List<String> dna);
}
