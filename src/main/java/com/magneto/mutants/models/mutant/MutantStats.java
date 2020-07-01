package com.magneto.mutants.models.mutant;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class MutantStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private Long countMutantDna;

    @Column
    private Long countHumanDna;

    @Column
    private Double ratio;

    @Column
    private LocalDateTime updatedDate;
}
