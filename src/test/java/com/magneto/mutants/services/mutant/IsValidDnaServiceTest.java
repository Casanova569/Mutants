package com.magneto.mutants.services.mutant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.magneto.mutants.services.mutant.IIsValidDnaService;
import com.magneto.mutants.services.mutant.impl.IsValidDnaService;
import java.util.List;
import org.junit.jupiter.api.Test;

public class IsValidDnaServiceTest {

    @Test
    void whenIsValidDnaThenReturnTrue() {
        final IIsValidDnaService isValidDnaService = new IsValidDnaService();
        final List<String> dna = List.of("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
        final boolean isValid = isValidDnaService.isValid(dna);
        assertTrue(isValid);
    }

    @Test
    void whenEmptyListThenReturnFalse() {
        final IIsValidDnaService isValidDnaService = new IsValidDnaService();
        final List<String> dna = List.of("");
        final boolean isValid = isValidDnaService.isValid(dna);
        assertFalse(isValid);
    }

    @Test
    void whenInvalidCharactersOnDnaThenReturnFalse() {
        final IIsValidDnaService isValidDnaService = new IsValidDnaService();
        final List<String> dna = List.of("aaaa", "bbbb", "cccc", "1xap");
        final boolean isValid = isValidDnaService.isValid(dna);
        assertFalse(isValid);
    }

    @Test
    void whenMatrixDnaIsNotSquareThenReturnFalse() {
        final IIsValidDnaService isValidDnaService = new IsValidDnaService();
        final List<String> dna = List.of("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","T");
        final boolean isValid = isValidDnaService.isValid(dna);
        assertFalse(isValid);
    }
}
