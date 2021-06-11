package com.w2m.superheros.service;

import com.w2m.superheros.domain.SuperHero;
import com.w2m.superheros.exception.ResourceNotFoundException;
import com.w2m.superheros.repository.SuperHeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SuperHeroService {

    @Autowired
    private SuperHeroRepository superHeroRepository;

    public List<SuperHero> findAll() {
        return superHeroRepository.findAll();
    }

    public Optional<SuperHero> findById(Long id) throws Exception {
        return superHeroRepository.findById(id);
    }

    public List<SuperHero> findByName(String name) {
        return superHeroRepository.findByNameContainingIgnoreCase(name);
    }

    public SuperHero update(Long id, SuperHero superHeroDetails) throws Exception {
        SuperHero superHero = superHeroRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("SuperHero not found with id " + id));;
        superHero.setName(superHeroDetails.getName());
        superHero.setFullName(superHeroDetails.getFullName());
        superHero.setPlaceOfBirth(superHeroDetails.getPlaceOfBirth());
        return superHeroRepository.save(superHero);
    }

    public void delete (Long id) throws Exception{
        SuperHero superHero = superHeroRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("SuperHero not found with id " + id));;
        superHeroRepository.delete(superHero);
    }

}
