package com.magneto.mutants.services.mutantstats;

import com.google.inject.ImplementedBy;
import com.magneto.mutants.services.mutantstats.impl.MutantStatsService;

@FunctionalInterface
@ImplementedBy(MutantStatsService.class)
public interface IMutantStatsService {

    boolean updateMutantStats(boolean isMutant);
}
