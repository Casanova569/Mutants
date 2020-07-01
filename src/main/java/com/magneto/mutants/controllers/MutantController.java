package com.magneto.mutants.controllers;

import com.magneto.mutants.models.mutant.Mutant;
import com.magneto.mutants.models.mutant.MutantDto;
import com.magneto.mutants.services.mutant.IMutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mutant")
public class MutantController {

    @Autowired
    private IMutantService mutantService;

    @PostMapping("/")
    public Mutant create(@RequestBody final MutantDto mutantDto) {
        return mutantService.createMutant(mutantDto);
    }
}
