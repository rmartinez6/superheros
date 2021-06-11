package com.w2m.superheros.service;

import com.w2m.superheros.domain.SuperHero;
import com.w2m.superheros.exception.ResourceNotFoundException;
import com.w2m.superheros.repository.SuperHeroRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class SuperHeroServiceTest {

    @Autowired
    private SuperHeroService superHeroService;

    @MockBean
    private SuperHeroRepository superHeroRepository;

    private static final SuperHero superman = new SuperHero(1L, "Superman", "Clark Joseph Kent", "Metropolis");
    private static final SuperHero thor = new SuperHero(2L, "Thor","Thor Odinson","Asgard");
    private static final SuperHero batman = new SuperHero(3L, "Batman","Bruce Wayne'","Ciudad Gotica");


    @Test
    void getAllSuperHeros(){
        List<SuperHero> superHeroListExpected = List.of(superman, thor);
        when(superHeroRepository.findAll()).thenReturn(superHeroListExpected);
        List<SuperHero> superHeroList = superHeroService.findAll();
        assertEquals(superHeroListExpected.size(), superHeroList.size());
    }

    @Test
    void getSuperHeroById(){
        when(superHeroRepository.findById(1L)).thenReturn(Optional.of(superman));
        Optional<SuperHero> superHero = superHeroService.findById(1L);
        assertTrue(superHero.isPresent());
        assertEquals(superman.getName(), superHero.get().getName());
    }

    @Test
    void getSuperHerosByName(){
        List<SuperHero> superHeroListExpected = List.of(superman, batman);
        String valueName = "man";
        when(superHeroRepository.findByNameContainingIgnoreCase(valueName)).thenReturn(superHeroListExpected);
        List<SuperHero> superHeroList = superHeroService.findByName(valueName);
        assertEquals(superHeroListExpected.size(), superHeroList.size());
    }

    @Test
    void updateSuperHeroById() throws ResourceNotFoundException {
        SuperHero supermanUpdated = superman;
        supermanUpdated.setPlaceOfBirth("Cordoba");
        when(superHeroRepository.findById(1L)).thenReturn(Optional.of(superman));
        when(superHeroRepository.save(supermanUpdated)).thenReturn(supermanUpdated);
        SuperHero superHero = superHeroService.update(1L, supermanUpdated);
        assertEquals(supermanUpdated.getName(), superHero.getName());
    }

    @Test
    void errorWhenUpdatedSuperHeroWithIdNotFound(){
        SuperHero supermanUpdated = superman;
        supermanUpdated.setPlaceOfBirth("Cordoba");
        when(superHeroRepository.findById(20L)).thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            superHeroService.update(20L, supermanUpdated);
        });
    }

    @Test
    void deleteSuperHeroById() {
        when(superHeroRepository.findById(1L)).thenReturn(Optional.of(superman));
        Mockito.doNothing().when(superHeroRepository).delete(superman);
        Assertions.assertDoesNotThrow(() -> {
            superHeroService.delete(1L);
        });
    }


}
