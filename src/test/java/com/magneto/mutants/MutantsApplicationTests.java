package com.magneto.mutants;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class MutantsApplicationTests {

	/*

	    private boolean checkDiagonalRightToLeft(final List<String> dna, final int index, final int limit) {
        List<String> secondaryDiagonal = new ArrayList<>();
        List<String> upperDiagonal = new ArrayList<>();
        List<String> bottomDiagonal = new ArrayList<>();
        boolean isMutantDNA = false;
        if (index == 0) {
            for(int x = index; x < limit; x++) {
                secondaryDiagonal.add(dna.get(x).substring(limit - 1, limit));
            }
            isMutantDNA = mutantDNA.stream().anyMatch(secondaryDiagonal::contains);
        } else {
            for(int x = index; x < limit; x++) {
                upperDiagonal.add(dna.get(x - 1).substring(x, x + 1));
                bottomDiagonal.add(dna.get(x).substring(x, x + 1));
            }
            isMutantDNA = mutantDNA.stream().anyMatch(upperDiagonal::contains)
                    || mutantDNA.stream().anyMatch(bottomDiagonal::contains);
        }
        return isMutantDNA;
    }

	 */

	@Test
	void COSAS() {
		List<String> dna = List.of("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTGt");
		List<String> dnaUp = dna.stream().map(String::toUpperCase).collect(Collectors.toList());
		int y = 2;
	}
}
