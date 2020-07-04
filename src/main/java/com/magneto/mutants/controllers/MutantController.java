package com.magneto.mutants.controllers;

import com.magneto.mutants.models.mutant.Mutant;
import com.magneto.mutants.models.mutant.MutantDto;
import com.magneto.mutants.services.mutant.IMutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mutant")
public class MutantController {

    private final IMutantService mutantService;

    @Autowired
    public MutantController(final IMutantService mutantService){
        this.mutantService = mutantService;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Mutant create(@RequestBody final MutantDto mutantDto) {
        return mutantService.createMutant(mutantDto);
    }
}
