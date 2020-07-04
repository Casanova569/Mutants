package com.magneto.mutants.models.mutant;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class MutantDto {

    private List<String> dna;
}
