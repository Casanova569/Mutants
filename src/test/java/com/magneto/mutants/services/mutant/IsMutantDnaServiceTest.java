package com.magneto.mutants.services.mutant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.magneto.mutants.services.mutant.impl.IsMutantDnaService;
import java.util.List;
import org.junit.jupiter.api.Test;

public class IsMutantDnaServiceTest {

    @Test
    void whenIsMutantThenReturnTrue() {
        final IIsMutantDnaService isMutantDnaService = new IsMutantDnaService();
        final List<String> dna = List.of("AAAAGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
        final boolean isMutant = isMutantDnaService.isMutant(dna);
        assertTrue(isMutant);
    }

    @Test
    void whenItMatrixHadFewElementsThenReturnFalse() {
        final IIsMutantDnaService isMutantDnaService = new IsMutantDnaService();
        final List<String> dna = List.of("AA","CC");
        final boolean isMutant = isMutantDnaService.isMutant(dna);
        assertFalse(isMutant);
    }

    @Test
    void whenIsNotMutantThenReturnFalse() {
        final IIsMutantDnaService isMutantDnaService = new IsMutantDnaService();
        final List<String> dna = List.of("ACGTA","ACGTA","TGTCA","GACTT","AGGAA");
        final boolean isMutant = isMutantDnaService.isMutant(dna);
        assertFalse(isMutant);
    }
}
