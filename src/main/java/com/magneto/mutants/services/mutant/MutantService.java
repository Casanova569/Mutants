package com.magneto.mutants.services.mutant;

import com.magneto.mutants.exceptions.MutantDNAExepction;
import com.magneto.mutants.models.mutant.Mutant;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class MutantService {

    public static final List<String> mutantDNA = List.of("AAAA", "CCCC", "GGGG", "TTTT");

    public Mutant createMutant(final Mutant mutant) {
        List<String> dna = mutant.getDna();
        final boolean isValid = isValidDNA(dna);
        if (!isValid) {
            throw new MutantDNAExepction("The matrix given is invalid");
        }
        dna = dna.stream().map(String::toUpperCase).collect(Collectors.toList());
        if (isMutant(dna)) {
            // 2 REPOSITORIES
        } else {
            // 1 REPOSITORY
        }
        return mutant;
    /*
    try {
      return new Mutant();
    } catch (ExecutionException e) {
      throw new MutantServiceException("Failed to post Mutant in MutantService");
    }

     */
    }

    private boolean isMutant(final List<String> dna) {
        final int listSize = dna.size();
        int x = 0;
        boolean isMutantDNA = false;
        while (x < listSize && !isMutantDNA) {
            if (checkHorizontalSequence(dna,x) || checkVerticalSequence(dna,x)
                    || checkDiagonalLeftToRight(dna,x,listSize)) {
                isMutantDNA = true;
            }
            x++;
        }
        return isMutantDNA;
    }

    private boolean checkHorizontalSequence(final List<String> dna, final int index) {
        return mutantDNA.stream().anyMatch(dna.get(index)::contains);
    }

    private boolean checkVerticalSequence(final List<String> dna, final int index) {
        final String column = dna.stream().map(s -> s.substring(index, index + 1)).collect(Collectors.joining());
        return mutantDNA.stream().anyMatch(column::contains);
    }

    private boolean checkDiagonalLeftToRight(final List<String> dna, final int index, final int limit) {
        List<String> mainDiagonal = new ArrayList<>();
        List<String> upperDiagonal = new ArrayList<>();
        List<String> bottomDiagonal = new ArrayList<>();
        boolean isMutantDNA = false;
        int startIndex = 0;
        if (index == 0) {
            for(int x = index; x < limit; x++) {
                mainDiagonal.add(dna.get(x).substring(x, x + 1));
            }
            isMutantDNA = mutantDNA.stream().anyMatch(mainDiagonal::contains);
        } else {
            for(int x = index; x < limit; x++) {
                upperDiagonal.add(dna.get(startIndex).substring(x, x + 1));
                bottomDiagonal.add(dna.get(x).substring(startIndex, startIndex + 1));
                startIndex++;
            }
            isMutantDNA = mutantDNA.stream().anyMatch(upperDiagonal::contains)
                    || mutantDNA.stream().anyMatch(bottomDiagonal::contains);
        }
        return isMutantDNA;
    }

    private boolean checkDiagonalRightToLeft(final List<String> dna, final int index, final int limit) {
        List<String> secondaryDiagonal = new ArrayList<>();
        List<String> upperDiagonal = new ArrayList<>();
        List<String> bottomDiagonal = new ArrayList<>();
        boolean isMutantDNA = false;
        int startIndex = 0;
        if (index == 0) {
            for(int x = index; x < limit; x++) {
                secondaryDiagonal.add(dna.get(x).substring(limit - x - 1, limit - x));
            }
            isMutantDNA = mutantDNA.stream().anyMatch(secondaryDiagonal::contains);
        } else {
            for(int x = index; x < limit; x++) {
                upperDiagonal.add(dna.get(startIndex).substring(limit - x - 1, limit - x));
                bottomDiagonal.add(dna.get(x).substring(limit - startIndex - 1, limit - startIndex));
                startIndex++;
            }
            isMutantDNA = mutantDNA.stream().anyMatch(upperDiagonal::contains)
                    || mutantDNA.stream().anyMatch(bottomDiagonal::contains);
        }
        return isMutantDNA;
    }

    private boolean isValidDNA(final List<String> dna) {
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
