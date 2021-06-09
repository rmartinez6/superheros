package com.w2m.superheros.service;

import com.w2m.superheros.domain.SuperHero;
import com.w2m.superheros.repository.SuperHeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SuperHeroService {

    @Autowired
    private SuperHeroRepository superHeroRepository;

    public List<SuperHero> findAll() {
        return superHeroRepository.findAll();
    }

}
