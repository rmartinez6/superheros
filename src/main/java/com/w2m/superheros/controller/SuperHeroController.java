package com.w2m.superheros.controller;

import com.w2m.superheros.domain.SuperHero;
import com.w2m.superheros.exception.ResourceNotFoundException;
import com.w2m.superheros.service.SuperHeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(path = "/superheros")
public class SuperHeroController {

    @Autowired
    SuperHeroService superHeroService;

    @GetMapping()
    public ResponseEntity get(@RequestParam(required = false) String name){
        List<SuperHero> superHeroList;
        superHeroList =
                (name==null || name.isEmpty())?
                superHeroService.findAll():
                superHeroService.findByName(name);
        return ResponseEntity.ok().body(superHeroList);

    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        SuperHero superHero = superHeroService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SuperHero not found with id " + id));
        return ResponseEntity.ok().body(superHero);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody SuperHero superHero) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(superHeroService.update(id, superHero));
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity delete(@PathVariable("id") Long id) throws ResourceNotFoundException {
        superHeroService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
