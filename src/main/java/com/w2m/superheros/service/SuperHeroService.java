package com.w2m.superheros.service;

import com.w2m.superheros.domain.SuperHero;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class SuperHeroService {

    public List<SuperHero> findAll() {
        return new ArrayList<>();
    }

}
