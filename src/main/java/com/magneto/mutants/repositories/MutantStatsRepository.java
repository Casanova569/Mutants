package com.magneto.mutants.repositories;

import com.magneto.mutants.models.mutant.MutantStats;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MutantStatsRepository extends JpaRepository<MutantStats, Long> {

    @Query("select ms from MutantStats ms order by ms.id desc")
    public Optional<MutantStats> findLastId(PageRequest pageRequest);
}
