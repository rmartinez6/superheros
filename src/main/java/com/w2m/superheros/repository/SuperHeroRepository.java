package com.w2m.superheros.repository;

import com.w2m.superheros.domain.SuperHero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository()
public interface SuperHeroRepository extends JpaRepository<SuperHero, Long> {

    List<SuperHero> findByNameContainingIgnoreCase(String name);

}
