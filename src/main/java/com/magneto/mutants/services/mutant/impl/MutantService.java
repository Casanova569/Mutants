package com.magneto.mutants.services.mutant.impl;

import com.magneto.mutants.exceptions.MutantDnaException;
import com.magneto.mutants.exceptions.MutantServiceException;
import com.magneto.mutants.exceptions.NotMutantDnaForbiddenException;
import com.magneto.mutants.models.mutant.Mutant;
import com.magneto.mutants.models.mutant.MutantDto;
import com.magneto.mutants.repositories.MutantRepository;
import com.magneto.mutants.services.mutant.IIsMutantDnaService;
import com.magneto.mutants.services.mutant.IIsValidDnaService;
import com.magneto.mutants.services.mutant.IMutantService;
import com.magneto.mutants.services.mutantstats.IMutantStatsService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MutantService implements IMutantService {

    private final MutantRepository mutantRepository;

    private final IIsValidDnaService isValidDna;

    private final IIsMutantDnaService isMutantDna;

    private final IMutantStatsService mutantStatsService;

    @Autowired
    public MutantService(final MutantRepository mutantRepository,
            final IIsValidDnaService isValidDna,
            final IIsMutantDnaService isMutantDna,
            final IMutantStatsService mutantStatsService) {
        this.mutantRepository = mutantRepository;
        this.isValidDna = isValidDna;
        this.isMutantDna = isMutantDna;
        this.mutantStatsService = mutantStatsService;
    }

    public Mutant createMutant(MutantDto mutantDto) {
        List<String> dna = mutantDto.getDna();
        final boolean isValid = isValidDna.isValid(dna);
        if (!isValid) {
            throw new MutantDnaException("The matrix given is invalid");
        }
        dna = dna.stream().map(String::toUpperCase).collect(Collectors.toList());
        final Mutant mutant = new Mutant(mutantDto);
        final boolean isMutant = isMutantDna.isMutant(dna);
        mutantStatsService.updateMutantStats(isMutant);
        if (!isMutant) {
            throw new NotMutantDnaForbiddenException("Humans are not allowed here");
        }
        try {
            return mutantRepository.save(mutant);
        } catch (Exception e) {
            throw new MutantServiceException("Failed MutantService to save mutant", e);
        }

    }
}
