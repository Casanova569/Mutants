package com.magneto.mutants.services.mutant.impl;

import com.magneto.mutants.services.mutant.IIsValidDnaService;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class IsValidDnaService implements IIsValidDnaService {

    public boolean isValid(final List<String> dna) {
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
