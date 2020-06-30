package com.magneto.mutants.repositories;

import com.magneto.mutants.models.mutant.Mutant;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface MutantRepository extends CrudRepository<Mutant, Long>{

}
