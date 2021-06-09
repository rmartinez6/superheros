package com.w2m.superheros.controller;

import com.w2m.superheros.domain.SuperHero;
import com.w2m.superheros.exception.ResourceNotFoundException;
import com.w2m.superheros.service.SuperHeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(path = "/superheros")
public class SuperHeroController {

    @Autowired
    SuperHeroService superHeroService;

    @GetMapping()
    public ResponseEntity getAll(){
        return ResponseEntity.ok().body(superHeroService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok().body(superHeroService.findById(id).get());
    }

}
