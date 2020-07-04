package com.magneto.mutants.models.mutant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
public class MutantStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    @ApiModelProperty
    private Long countMutantDna;

    @Column
    @ApiModelProperty
    private Long countHumanDna;

    @Column
    @ApiModelProperty
    private Double ratio;

    @Column
    @ApiModelProperty
    private LocalDateTime updatedDate;
}
