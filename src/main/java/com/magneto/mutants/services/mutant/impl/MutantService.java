package com.magneto.mutants.services.mutant.impl;

import com.magneto.mutants.exceptions.MutantDnaException;
import com.magneto.mutants.models.mutant.Mutant;
import com.magneto.mutants.repositories.MutantRepository;
import com.magneto.mutants.services.mutant.IIsMutantDnaService;
import com.magneto.mutants.services.mutant.IIsValidDnaService;
import com.magneto.mutants.services.mutant.IMutantService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MutantService implements IMutantService {

    @Autowired
    private MutantRepository mutantRepository;

    @Autowired
    private IIsValidDnaService isValidDna;

    @Autowired
    private IIsMutantDnaService isMutantDna;

    public Mutant createMutant(Mutant mutant) {
        List<String> dna = mutant.getDna();
        final boolean isValid = isValidDna.isValid(dna);
        if (!isValid) {
            throw new MutantDnaException("The matrix given is invalid");
        }
        dna = dna.stream().map(String::toUpperCase).collect(Collectors.toList());

        if (3 < 2) {
            mutantRepository.save(mutant);
        }
        /*
        if (isMutant(dna)) {
            // 2 REPOSITORIES
            return mutantRepository.save(mutant);
        } else {
            // 1 REPOSITORY
        }
         */
        return mutant;
    }
}
