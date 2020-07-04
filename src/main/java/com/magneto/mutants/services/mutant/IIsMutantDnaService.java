package com.magneto.mutants.services.mutant;

import com.google.inject.ImplementedBy;
import com.magneto.mutants.services.mutant.impl.IsMutantDnaService;
import java.util.List;

@FunctionalInterface
@ImplementedBy(IsMutantDnaService.class)
public interface IIsMutantDnaService {

    boolean isMutant(List<String> dna);
}
