package com.magneto.mutants.controllers;

import com.magneto.mutants.models.mutant.Mutant;
import com.magneto.mutants.services.mutant.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mutant")
public class MutantController {

    @Autowired
    private MutantService mutantService;

    @PostMapping("/")
    public Mutant create(@RequestBody final Mutant mutant) {
        //final Mutant createdMutant = m
        return new Mutant();
    }
}
