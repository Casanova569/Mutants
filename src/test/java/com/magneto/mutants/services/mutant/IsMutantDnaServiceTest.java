package com.magneto.mutants.services.mutant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.magneto.mutants.services.mutant.impl.IsMutantDnaService;
import java.util.List;
import org.junit.jupiter.api.Test;

public class IsMutantDnaServiceTest {

    @Test
    void whenIsMutantInHorizontalSequenceThenReturnTrue() {
        final IIsMutantDnaService isMutantDnaService = new IsMutantDnaService();
        final List<String> dna = List.of("AAAAGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
        final boolean isMutant = isMutantDnaService.isMutant(dna);
        assertTrue(isMutant);
    }

    @Test
    void whenIsMutantInVerticalSequenceThenReturnTrue() {
        final IIsMutantDnaService isMutantDnaService = new IsMutantDnaService();
        final List<String> dna = List.of("CAGTGC","CAGTGC","CTATGT","CGAAGG","CCCCTA","TCACTG");
        final boolean isMutant = isMutantDnaService.isMutant(dna);
        assertTrue(isMutant);
    }

    @Test
    void whenIsMutantInMainDiagonalLeftToRightThenReturnTrue() {
        final IIsMutantDnaService isMutantDnaService = new IsMutantDnaService();
        final List<String> dna = List.of("ACTG","GACT","TGAC","CTGA");
        final boolean isMutant = isMutantDnaService.isMutant(dna);
        assertTrue(isMutant);
    }

    @Test
    void whenIsMutantInDiagonalLeftToRightNotTheMainDiagonalThenReturnTrue() {
        final IIsMutantDnaService isMutantDnaService = new IsMutantDnaService();
        final List<String> dna = List.of("ACGTA","AAGGT","TACCT","ACAGG", "ACGAT");
        final boolean isMutant = isMutantDnaService.isMutant(dna);
        assertTrue(isMutant);
    }

    @Test
    void whenIsMutantInSecondaryDiagonalRightToLeftThenReturnTrue() {
        final IIsMutantDnaService isMutantDnaService = new IsMutantDnaService();
        final List<String> dna = List.of("CCTA","GAAT","TAAC","ATGA");
        final boolean isMutant = isMutantDnaService.isMutant(dna);
        assertTrue(isMutant);
    }

    @Test
    void whenIsMutantInDiagonalLeftToRightNotTheSecondaryDiagonalThenReturnTrue() {
        final IIsMutantDnaService isMutantDnaService = new IsMutantDnaService();
        final List<String> dna = List.of("ACGTA","AAGGT","TACTT","ACTGG", "ATGAT");
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
