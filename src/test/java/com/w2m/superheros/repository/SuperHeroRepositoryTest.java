package com.w2m.superheros.repository;

import com.w2m.superheros.domain.SuperHero;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class SuperHeroRepositoryTest {
    @Autowired
    SuperHeroRepository superHeroRepository;
    @Autowired
    private TestEntityManager entityManager;

    private static final SuperHero superman = new SuperHero(1L, "Superman", "Clark Joseph Kent", "Metropolis");
    private static final SuperHero thor = new SuperHero(2L, "Thor","Thor Odinson","Asgard");
    private static final SuperHero batman = new SuperHero(3L, "Batman","Bruce Wayne'","Ciudad Gotica");

    @BeforeEach
    public void setUp() {
        entityManager.persist(superman);
        entityManager.persist(thor);
        entityManager.persist(batman);
    }

    @AfterEach
    public void destroyAll() {
        entityManager.clear();
    }

    @Test
    public void returnAllSuperHerosWhenFindAll() {
        List<SuperHero> superHeroList = superHeroRepository.findAll();
        assertEquals(3, superHeroList.size());
    }

    @Test
    public void returnASuperHeroWhenFindById() {
        Optional<SuperHero> superHero = superHeroRepository.findById(2L);
        assertEquals(thor, superHero.get());
    }

    @Test
    public void returnSuperHerosWhenFindByName() {
        List<SuperHero> superHeroList = superHeroRepository.findByNameContainingIgnoreCase("man");
        assertEquals(2, superHeroList.size());
        assertTrue(superHeroList.containsAll(List.of(superman, batman)));
    }

    @Test
    public void updateSuperHeroData() {
        SuperHero supermanUpdated = superman;
        supermanUpdated.setPlaceOfBirth("Cordoba");
        superHeroRepository.save(supermanUpdated);
        assertEquals("Cordoba", superHeroRepository.findById(1L).get().getPlaceOfBirth());
    }
}



