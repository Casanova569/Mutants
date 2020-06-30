package com.magneto.mutants.services.mutant;

import com.magneto.mutants.exceptions.MutantDnaException;
import com.magneto.mutants.models.mutant.Mutant;
import com.magneto.mutants.repositories.MutantRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MutantService {

    private static final List<String> MUTANT_DNA_SEQUENCES = List.of("AAAA", "CCCC", "GGGG", "TTTT");

    @Autowired
    private MutantRepository mutantRepository;

    public Mutant createMutant(Mutant mutant) {
        List<String> dna = mutant.getDna();
        final boolean isValid = isValidDna(dna);
        if (!isValid) {
            throw new MutantDnaException("The matrix given is invalid");
        }
        dna = dna.stream().map(String::toUpperCase).collect(Collectors.toList());

        if (3 < 2) {
            mutantRepository.save(mutant);
        }
        /*
        if (isMutant(dna)) {
            return mutantRepository.save(mutant);
        } else {
            // 1 REPOSITORY
        }
         */
        return mutant;
    }

    private boolean isMutant(final List<String> dna) {
        final int listSize = dna.size();
        int x = 0;
        boolean isMutantDna = false;
        while (x < listSize && !isMutantDna) {
            if (checkHorizontalSequence(dna,x) || checkVerticalSequence(dna,x)
                    || checkDiagonalLeftToRight(dna,x,listSize)) {
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
        final String column = dna.stream().map(s -> s.substring(index, index + 1)).collect(Collectors.joining());
        return MUTANT_DNA_SEQUENCES.stream().anyMatch(column::contains);
    }

    private boolean checkDiagonalLeftToRight(final List<String> dna, final int index, final int limit) {
        List<String> mainDiagonal = new ArrayList<>();
        List<String> upperDiagonal = new ArrayList<>();
        List<String> bottomDiagonal = new ArrayList<>();
        boolean isMutantDna = false;
        int startIndex = 0;
        if (index == 0) {
            for(int x = index; x < limit; x++) {
                mainDiagonal.add(dna.get(x).substring(x, x + 1));
            }
            isMutantDna = MUTANT_DNA_SEQUENCES.stream().anyMatch(mainDiagonal::contains);
        } else {
            for(int x = index; x < limit; x++) {
                upperDiagonal.add(dna.get(startIndex).substring(x, x + 1));
                bottomDiagonal.add(dna.get(x).substring(startIndex, startIndex + 1));
                startIndex++;
            }
            isMutantDna = MUTANT_DNA_SEQUENCES.stream().anyMatch(upperDiagonal::contains)
                    || MUTANT_DNA_SEQUENCES.stream().anyMatch(bottomDiagonal::contains);
        }
        return isMutantDna;
    }

    private boolean checkDiagonalRightToLeft(final List<String> dna, final int index, final int limit) {
        List<String> secondaryDiagonal = new ArrayList<>();
        List<String> upperDiagonal = new ArrayList<>();
        List<String> bottomDiagonal = new ArrayList<>();
        boolean isMutantDna = false;
        int startIndex = 0;
        if (index == 0) {
            for(int x = index; x < limit; x++) {
                secondaryDiagonal.add(dna.get(x).substring(limit - x - 1, limit - x));
            }
            isMutantDna = MUTANT_DNA_SEQUENCES.stream().anyMatch(secondaryDiagonal::contains);
        } else {
            for(int x = index; x < limit; x++) {
                upperDiagonal.add(dna.get(startIndex).substring(limit - x - 1, limit - x));
                bottomDiagonal.add(dna.get(x).substring(limit - startIndex - 1, limit - startIndex));
                startIndex++;
            }
            isMutantDna = MUTANT_DNA_SEQUENCES.stream().anyMatch(upperDiagonal::contains)
                    || MUTANT_DNA_SEQUENCES.stream().anyMatch(bottomDiagonal::contains);
        }
        return isMutantDna;
    }

    private boolean isValidDna(final List<String> dna) {
        if (dna.isEmpty()) {
            return false;
        }
        boolean isCharactersValid = dna.stream().allMatch(s -> Pattern.matches("[ACGTacgt]+", s));
        if (!isCharactersValid) {
            return false;
        }
        final int rowSize = dna.size();
        final List<Integer> columnListSizes = dna.stream().map(String::length)
                .collect(Collectors.toList());
        // Check if it is a square matrix
        return columnListSizes.stream().allMatch(columnSize -> columnSize == rowSize);
    }
}
