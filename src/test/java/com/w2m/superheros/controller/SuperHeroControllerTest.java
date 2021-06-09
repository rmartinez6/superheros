package com.w2m.superheros.controller;

import com.w2m.superheros.domain.SuperHero;
import com.w2m.superheros.service.SuperHeroService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest
public class SuperHeroControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private SuperHeroService superHeroService;


    @Test
    void getAllSuperHerosWhenCallGetAllSuperherosEndpoint() throws Exception {
        List<SuperHero> superHeroList = new ArrayList<SuperHero>();
        superHeroList.add(new SuperHero(1L,"Superman","Clark Joseph Kent","Metropolis"));
        superHeroList.add(new SuperHero(2L,"Batman","Bruce Wayne", "Ciudad Gotica"));
        when(superHeroService.findAll()).thenReturn(superHeroList);

        mockMvc.perform(MockMvcRequestBuilders.get("/superheros")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2))).andDo(print())
                .andExpect(jsonPath("$[0].name").value("Superman"))
                .andExpect(jsonPath("$[1].name").value("Batman"));
    }

    @Test
    void getASuperHeroWhenCallGetSuperHeroById() throws Exception {

        when(superHeroService.findById(1L)).thenReturn(Optional.of(new SuperHero(1L,"Superman","Clark Joseph Kent","Metropolis")));

        mockMvc.perform(MockMvcRequestBuilders.get("/superheros/1")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Superman"));
    }


}
