package com.magneto.mutants.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.magneto.mutants.models.mutant.Mutant;
import com.magneto.mutants.models.mutant.MutantDto;
import com.magneto.mutants.services.mutant.IMutantService;
import java.util.List;
import org.junit.jupiter.api.Test;

public class MutantControllerTest {

    @Test
    public void whenMutantReturnMutant() {
        final IMutantService mutantService = mock(IMutantService.class);
        when(mutantService.createMutant(any())).thenReturn(mockMutant());
        final MutantController mutantController = new MutantController(mutantService);
        final Mutant mutant = mutantController.create(mockMutantDto());
        assertEquals(mutant, mockMutant());
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
