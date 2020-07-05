package com.magneto.mutants.services.mutant.impl;

import com.magneto.mutants.services.mutant.IIsMutantDnaService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class IsMutantDnaService implements IIsMutantDnaService {

    private static final List<String> MUTANT_DNA_SEQUENCES = List.of("AAAA", "CCCC", "GGGG", "TTTT");
    private static final int MUTANT_DNA_SIZE_NEEDED_SEQUENCE = 4;
    private static final int MUTANT_DNA_SEQUENCES_NEEDED = 2;

    public boolean isMutant(final List<String> dna) {
        final int listSize = dna.size();
        if (hasFewElements(listSize)) {
            return false;
        }
        int x = 0;
        int count_sequences = 0;
        boolean isMutantDna = false;
        while (x < listSize && !isMutantDna) {
            count_sequences = checkHorizontalSequence(dna, x) ? count_sequences + 1 : count_sequences;
            count_sequences = checkVerticalSequence(dna, x) ? count_sequences + 1 : count_sequences;
            count_sequences = checkDiagonalLeftToRight(dna, x, listSize) ? count_sequences + 1 : count_sequences;
            count_sequences = checkDiagonalRightToLeft(dna, x, listSize) ? count_sequences + 1 : count_sequences;
            if (count_sequences >= MUTANT_DNA_SEQUENCES_NEEDED) {
                isMutantDna = true;
            }
            x++;
        }
        return isMutantDna;
    }

    private boolean checkHorizontalSequence(final List<String> dna, final int index) {
        return MUTANT_DNA_SEQUENCES.stream().anyMatch(dna.get(index)::contains);
    }

    private boolean checkVerticalSequence(final List<String> dna, final int index) {
        final String column = dna.stream().map(s -> s.substring(index, index + 1)).collect(
                Collectors.joining());
        return MUTANT_DNA_SEQUENCES.stream().anyMatch(column::contains);
    }

    private boolean checkDiagonalLeftToRight(final List<String> dna, final int index, final int limit) {
        final Integer sequenceSize = limit - index;
        if (hasFewElements(sequenceSize)) {
            return false;
        }
        String mainDiagonal = "";
        String upperDiagonal = "";
        String bottomDiagonal = "";
        boolean isMutantDna = false;
        int startIndex = 0;
        if (index == 0) {
            for(int x = index; x < limit; x++) {
                mainDiagonal = mainDiagonal + dna.get(x).substring(x, x + 1);
            }
            isMutantDna = MUTANT_DNA_SEQUENCES.stream().anyMatch(mainDiagonal::contains);
        } else {
            for(int x = index; x < limit; x++) {
                upperDiagonal = upperDiagonal + dna.get(startIndex).substring(x, x + 1);
                bottomDiagonal = bottomDiagonal + dna.get(x).substring(startIndex, startIndex + 1);
                startIndex++;
            }
            isMutantDna = MUTANT_DNA_SEQUENCES.stream().anyMatch(upperDiagonal::contains)
                    || MUTANT_DNA_SEQUENCES.stream().anyMatch(bottomDiagonal::contains);
        }
        return isMutantDna;
    }

    private boolean checkDiagonalRightToLeft(final List<String> dna, final int index, final int limit) {
        final Integer sequenceSize = limit - index;
        if (hasFewElements(sequenceSize)) {
            return false;
        }
        String secondaryDiagonal = "";
        String upperDiagonal = "";
        String bottomDiagonal = "";
        boolean isMutantDna = false;
        int startIndex = 0;
        if (index == 0) {
            for(int x = index; x < limit; x++) {
                secondaryDiagonal = secondaryDiagonal + dna.get(x).substring(limit - x - 1, limit - x);
            }
            isMutantDna = MUTANT_DNA_SEQUENCES.stream().anyMatch(secondaryDiagonal::contains);
        } else {
            for(int x = index; x < limit; x++) {
                upperDiagonal = upperDiagonal + dna.get(startIndex).substring(limit - x - 1, limit - x);
                bottomDiagonal = bottomDiagonal + dna.get(x).substring(limit - startIndex - 1, limit - startIndex);
                startIndex++;
            }
            isMutantDna = MUTANT_DNA_SEQUENCES.stream().anyMatch(upperDiagonal::contains)
                    || MUTANT_DNA_SEQUENCES.stream().anyMatch(bottomDiagonal::contains);
        }
        return isMutantDna;
    }

    private boolean hasFewElements(final int size) {
        return size < MUTANT_DNA_SIZE_NEEDED_SEQUENCE;
    }
}
