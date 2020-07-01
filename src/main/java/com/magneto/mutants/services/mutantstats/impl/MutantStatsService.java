package com.magneto.mutants.services.mutantstats.impl;

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

    @Autowired
    private MutantStatsRepository mutantStatsRepository;

    @Override
    public void updateMutantStats(boolean isMutant) {
        final Optional<MutantStats> mutantStatsOptional = mutantStatsRepository.findLastId(
                PageRequest.of(0, 1));
        MutantStats mutantStats = new MutantStats();
        Long count_human = 0L;
        Long count_mutant = 0L;
        if (!mutantStatsOptional.isEmpty()) {
            count_mutant = mutantStatsOptional.get().getCountMutantDna();
            count_human = mutantStatsOptional.get().getCountHumanDna();
        }
        count_human++;
        mutantStats.setCountHumanDna(count_human);
        if (isMutant) {
            count_mutant++;
        }
        mutantStats.setCountMutantDna(count_mutant);
        final Double ratio = Double.valueOf(count_mutant) / Double.valueOf(count_human);
        mutantStats.setRatio(ratio);
        mutantStats.setUpdatedDate(LocalDateTime.now());
        mutantStatsRepository.save(mutantStats);
    }
}
