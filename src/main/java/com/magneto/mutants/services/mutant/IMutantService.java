package com.magneto.mutants.services.mutant;

import com.google.inject.ImplementedBy;
import com.magneto.mutants.models.mutant.Mutant;
import com.magneto.mutants.services.mutant.impl.MutantService;

@FunctionalInterface
@ImplementedBy(MutantService.class)
public interface IMutantService {

    Mutant createMutant(Mutant mutant);
}
