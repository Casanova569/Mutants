package com.magneto.mutants.services.mutant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.magneto.mutants.exceptions.MutantDnaException;
import com.magneto.mutants.exceptions.MutantServiceException;
import com.magneto.mutants.exceptions.NotMutantDnaForbiddenException;
import com.magneto.mutants.models.mutant.Mutant;
import com.magneto.mutants.models.mutant.MutantDto;
import com.magneto.mutants.repositories.MutantRepository;
import com.magneto.mutants.services.mutant.IIsMutantDnaService;
import com.magneto.mutants.services.mutant.IIsValidDnaService;
import com.magneto.mutants.services.mutant.IMutantService;
import com.magneto.mutants.services.mutant.impl.MutantService;
import com.magneto.mutants.services.mutantstats.IMutantStatsService;
import java.util.List;
import org.junit.jupiter.api.Test;

public class MutantServiceTest {

    @Test
    void whenIsMutantThenReturnMutant() {
        final MutantRepository mutantRepository = mock(MutantRepository.class);
        final IIsMutantDnaService isMutantDna = mock(IIsMutantDnaService.class);
        final IIsValidDnaService isValidDna = mock(IIsValidDnaService.class);
        final IMutantStatsService mutantStats = mock(IMutantStatsService.class);
        when(mutantRepository.save(any())).thenReturn(mockMutant());
        when(isValidDna.isValid(any())).thenReturn(true);
        when(isMutantDna.isMutant(any())).thenReturn(true);
        when(mutantStats.updateMutantStats(true)).thenReturn(true);
        final IMutantService mutantService = new MutantService(mutantRepository, isValidDna, isMutantDna, mutantStats);
        final Mutant mutant = mutantService.createMutant(mockMutantDto());
        assertEquals(mutant, mockMutant());
    }

    @Test
    void whenIsNotMutantThenReturnNotMutantDnaForbiddenException() {
        final MutantRepository mutantRepository = mock(MutantRepository.class);
        final IIsMutantDnaService isMutantDna = mock(IIsMutantDnaService.class);
        final IIsValidDnaService isValidDna = mock(IIsValidDnaService.class);
        final IMutantStatsService mutantStats = mock(IMutantStatsService.class);
        when(mutantRepository.save(any())).thenReturn(mockMutant());
        when(isValidDna.isValid(any())).thenReturn(true);
        when(isMutantDna.isMutant(any())).thenReturn(false);
        when(mutantStats.updateMutantStats(true)).thenReturn(true);
        final IMutantService mutantService = new MutantService(mutantRepository, isValidDna, isMutantDna, mutantStats);
        assertThrows(NotMutantDnaForbiddenException.class,
                () -> mutantService.createMutant(mockMutantDto()));
    }

    @Test
    void whenInvalidDnaThenReturnBadRequestException() {
        final IIsValidDnaService isValidDna = mock(IIsValidDnaService.class);
        when(isValidDna.isValid(any())).thenReturn(false);
        final IMutantService mutantService = new MutantService(null, isValidDna, null, null);
        assertThrows(MutantDnaException.class,
                () -> mutantService.createMutant(mockMutantDto()));
    }

    @Test
    void whenFailedToPersistMutantThenReturnInternalServerError() {
        final MutantRepository mutantRepository = mock(MutantRepository.class);
        final IIsMutantDnaService isMutantDna = mock(IIsMutantDnaService.class);
        final IIsValidDnaService isValidDna = mock(IIsValidDnaService.class);
        final IMutantStatsService mutantStats = mock(IMutantStatsService.class);
        when(mutantRepository.save(any())).thenThrow(MutantServiceException.class);
        when(isValidDna.isValid(any())).thenReturn(true);
        when(isMutantDna.isMutant(any())).thenReturn(true);
        when(mutantStats.updateMutantStats(true)).thenReturn(true);
        final IMutantService mutantService = new MutantService(mutantRepository, isValidDna, isMutantDna, mutantStats);
        assertThrows(MutantServiceException.class,
                () -> mutantService.createMutant(mockMutantDto()));
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
