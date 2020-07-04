package com.magneto.mutants.services.mutantstats;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.magneto.mutants.exceptions.MutantStatsServiceException;
import com.magneto.mutants.models.mutant.MutantStats;
import com.magneto.mutants.repositories.MutantStatsRepository;
import com.magneto.mutants.services.mutantstats.impl.MutantStatsService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;

public class MutantStatsServiceTest {

    @Test
    void whenMutantStatsIsMutantThenReturnTrue() {
        final MutantStatsRepository mutantStatsRepository = mock(MutantStatsRepository.class);
        when(mutantStatsRepository.save(any())).thenReturn(mockMutantStatsIsMutant());
        final MutantStatsService mutantStatsService = new MutantStatsService(mutantStatsRepository);
        assertTrue(mutantStatsService.updateMutantStats(true));
    }

    @Test
    void whenMutantStatsIsHumanThenReturnTrue() {
        final MutantStatsRepository mutantStatsRepository = mock(MutantStatsRepository.class);
        when(mutantStatsRepository.save(any())).thenReturn(mockMutantStatsIsMutant());
        final MutantStatsService mutantStatsService = new MutantStatsService(mutantStatsRepository);
        assertTrue(mutantStatsService.updateMutantStats(false));
    }

    @Test
    void whenValidMutantStatsThenReturnTrue() {
        final MutantStatsRepository mutantStatsRepository = mock(MutantStatsRepository.class);
        when(mutantStatsRepository.save(any())).thenReturn(mockMutantStatsIsMutant());
        when(mutantStatsRepository.findLastId(PageRequest.of(0, 1))).thenReturn(Optional.of(mockMutantStatsIsMutant()));
        final MutantStatsService mutantStatsService = new MutantStatsService(mutantStatsRepository);
        assertTrue(mutantStatsService.updateMutantStats(false));
    }

    @Test
    void whenFailedToPersistMutantStatsThenReturnInternalServerError() {
        final MutantStatsRepository mutantStatsRepository = mock(MutantStatsRepository.class);
        when(mutantStatsRepository.save(any())).thenThrow(RuntimeException.class);
        final MutantStatsService mutantStatsService = new MutantStatsService(mutantStatsRepository);
        assertThrows(MutantStatsServiceException.class,
                () -> mutantStatsService.updateMutantStats(true));
    }

    @Test
    void whenGetMutantStatsThenReturnMutantStats() {
        final MutantStatsRepository mutantStatsRepository = mock(MutantStatsRepository.class);
        when(mutantStatsRepository.findLastId(any())).thenReturn(Optional.of(mockMutantStatsIsMutant()));
        final MutantStatsService mutantStatsService = new MutantStatsService(mutantStatsRepository);
        final MutantStats mutantStats = mutantStatsService.get();
        assertEquals(mockMutantStatsIsMutant(), mutantStats);
    }

    @Test
    void whenGetMutantStatsFailThenThrowInternalServerError() {
        final MutantStatsRepository mutantStatsRepository = mock(MutantStatsRepository.class);
        when(mutantStatsRepository.findLastId(any())).thenThrow(MutantStatsServiceException.class);
        final MutantStatsService mutantStatsService = new MutantStatsService(mutantStatsRepository);
        assertThrows(MutantStatsServiceException.class,
                () -> mutantStatsService.get());
    }

    private MutantStats mockMutantStatsIsMutant() {
        MutantStats mutantStats = new MutantStats();
        mutantStats.setCountMutantDna(1L);
        mutantStats.setCountHumanDna(1L);
        mutantStats.setRatio(1D);
        return mutantStats;
    }
}
