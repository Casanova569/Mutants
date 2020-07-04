package com.magneto.mutants.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.magneto.mutants.models.mutant.Mutant;
import com.magneto.mutants.models.mutant.MutantDto;
import com.magneto.mutants.models.mutant.MutantStats;
import com.magneto.mutants.services.mutant.IMutantService;
import com.magneto.mutants.services.mutantstats.IMutantStatsService;
import java.util.List;
import org.junit.jupiter.api.Test;

public class MutantControllerTest {

    @Test
    public void whenPostMutantThenReturnMutant() {
        final IMutantService mutantService = mock(IMutantService.class);
        when(mutantService.createMutant(any())).thenReturn(mockMutant());
        final MutantController mutantController = new MutantController(mutantService, null);
        final Mutant mutant = mutantController.create(mockMutantDto());
        assertEquals(mutant, mockMutant());
    }

    @Test
    public void whenGetMutantStatsThenReturnMutantStats() {
        final IMutantStatsService mutantStatsService = mock(IMutantStatsService.class);
        when(mutantStatsService.get()).thenReturn(mockMutantStats());
        final MutantController mutantController = new MutantController(null, mutantStatsService);
        final MutantStats mutantStats = mutantController.getMutantStats();
        assertEquals(mockMutantStats(), mutantStats);
    }

    private MutantStats mockMutantStats() {
        MutantStats mutantStats = new MutantStats();
        mutantStats.setCountHumanDna(1L);
        mutantStats.setCountMutantDna(1L);
        mutantStats.setRatio(1D);
        return mutantStats;
    }

    private Mutant mockMutant() {
        return new Mutant(mockMutantDto());
    }

    private MutantDto mockMutantDto() {
        final List<String> dna = List.of("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
        MutantDto mutantDto = new MutantDto();
        mutantDto.setDna(dna);
        return mutantDto;
    }
}
