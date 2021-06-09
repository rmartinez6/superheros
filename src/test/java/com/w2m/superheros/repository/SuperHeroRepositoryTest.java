package com.w2m.superheros.repository;

import com.w2m.superheros.domain.SuperHero;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class SuperHeroRepositoryTest {
    @Autowired
    SuperHeroRepository superHeroRepository;

    private static final SuperHero superman = new SuperHero(1L, "Superman", "Clark Joseph Kent", "Metropolis");
    private static final SuperHero thor = new SuperHero(2L, "Thor","Thor Odinson","Asgard");
    private static final SuperHero batman = new SuperHero(3L, "Batman","Bruce Wayne'","Ciudad Gotica");

    @BeforeEach
    public void setUp() {
        superHeroRepository.saveAll(List.of(superman, thor, batman));

    }

    @AfterEach
    public void destroyAll(){
        superHeroRepository.deleteAll();
    }

    @Test
    public void returnAllSuperHerosWhenFindAll() {
        List<SuperHero> superHeroList = superHeroRepository.findAll();
        assertTrue(superHeroList.containsAll(List.of(superman, thor, batman)));
        assertEquals(3, superHeroList.size());
    }
}



