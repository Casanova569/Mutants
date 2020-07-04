package com.magneto.mutants.models.mutant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@ApiModel
public class Mutant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    @ApiModelProperty(notes = "The dna will be persisted if it is from a mutant")
    private String dna;

    public Mutant(final MutantDto mutantDto) {
        this.dna = mutantDto.getDna().toString();
    }
}
