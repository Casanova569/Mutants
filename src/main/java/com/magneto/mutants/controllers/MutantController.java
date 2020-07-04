package com.magneto.mutants.controllers;

import com.magneto.mutants.models.mutant.Mutant;
import com.magneto.mutants.models.mutant.MutantDto;
import com.magneto.mutants.models.mutant.MutantStats;
import com.magneto.mutants.services.mutant.IMutantService;
import com.magneto.mutants.services.mutantstats.IMutantStatsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mutant")
@Api
public class MutantController {

    private final IMutantService mutantService;
    private final IMutantStatsService mutantStatsService;

    @Autowired
    public MutantController(final IMutantService mutantService,
                final IMutantStatsService mutantStatsService){
        this.mutantService = mutantService;
        this.mutantStatsService = mutantStatsService;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Return Mutant if the dna received had the mutant genome")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The dna from the mutant is persisted"),
            @ApiResponse(code = 400, message = "The dna received was invalid"),
            @ApiResponse(code = 403, message = "The dna was not mutant"),
            @ApiResponse(code = 500, message = "Failed to persist the dna")
    })
    public Mutant create(@RequestBody final MutantDto mutantDto) {
        return mutantService.createMutant(mutantDto);
    }

    @GetMapping("/stats")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Return the stats of mutant stats")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Get the mutant stats"),
            @ApiResponse(code = 500, message = "Failed to get mutant stats")
    })
    public MutantStats getMutantStats() {
        return mutantStatsService.get();
    }
}
