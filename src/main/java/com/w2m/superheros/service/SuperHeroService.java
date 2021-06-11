package com.w2m.superheros.service;

import com.w2m.superheros.domain.SuperHero;
import com.w2m.superheros.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface SuperHeroService {

    List<SuperHero> findAll();

    Optional<SuperHero> findById(Long id);

    List<SuperHero> findByName(String name);

    SuperHero update(Long id, SuperHero superHeroDetails) throws ResourceNotFoundException;

    void delete (Long id) throws ResourceNotFoundException;
}
