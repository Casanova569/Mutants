package com.magneto.mutants.services.mutant.impl;

import com.magneto.mutants.services.mutant.IIsValidDnaService;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class IsValidDnaService implements IIsValidDnaService {

    public boolean isValid(final List<String> dna) {
        if (dna.isEmpty() || !isCharactersValid(dna)) {
            return false;
        }
        return isSquareMatrix(dna);
    }

    private boolean isCharactersValid(final List<String> dna) {
        return dna.stream().allMatch(s -> Pattern.matches("[ACGTacgt]+", s));
    }

    private boolean isSquareMatrix(final List<String> dna) {
        final int rowSize = dna.size();
        final List<Integer> columnListSizes = dna.stream().map(String::length)
                .collect(Collectors.toList());
        return columnListSizes.stream().allMatch(columnSize -> columnSize == rowSize);
    }
}
