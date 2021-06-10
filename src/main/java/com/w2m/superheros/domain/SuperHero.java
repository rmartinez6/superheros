package com.w2m.superheros.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="SUPERHERO")
@Builder
public class SuperHero {

    @Id
    @Column(name="ID")
    private Long id;
    @Column(name="NAME")
    private String name;
    @Column(name="FULLNAME")
    private String fullName;
    @Column(name="PLACE_OF_BIRTH")
    private String placeOfBirth;

}
