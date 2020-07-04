package com.magneto.mutants.services.mutantstats.impl;

import com.magneto.mutants.exceptions.MutantStatsServiceException;
import com.magneto.mutants.models.mutant.MutantStats;
import com.magneto.mutants.repositories.MutantStatsRepository;
import com.magneto.mutants.services.mutantstats.IMutantStatsService;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class MutantStatsService implements IMutantStatsService {

    private final MutantStatsRepository mutantStatsRepository;

    @Autowired
    public MutantStatsService(final MutantStatsRepository mutantStatsRepository) {
        this.mutantStatsRepository = mutantStatsRepository;
    }

    @Override
    public boolean updateMutantStats(boolean isMutant) {
        final Optional<MutantStats> mutantStatsOptional = mutantStatsRepository.findLastId(
                PageRequest.of(0, 1));
        MutantStats mutantStats = new MutantStats();
        Long countHuman = mutantStatsOptional.isEmpty() ? 0L : mutantStatsOptional.get().getCountHumanDna();
        Long countMutant = mutantStatsOptional.isEmpty() ? 0L : mutantStatsOptional.get().getCountMutantDna();
        countHuman++;
        mutantStats.setCountHumanDna(countHuman);
        if (isMutant) {
            countMutant++;
        }
        mutantStats.setCountMutantDna(countMutant);
        final Double ratio = Double.valueOf(countMutant) / Double.valueOf(countHuman);
        mutantStats.setRatio(ratio);
        mutantStats.setUpdatedDate(LocalDateTime.now());
        try {
            mutantStatsRepository.save(mutantStats);
            return true;
        } catch (Exception e) {
            throw new MutantStatsServiceException("Failed MutantStatsService to save mutant stats", e);
        }
    }
}
