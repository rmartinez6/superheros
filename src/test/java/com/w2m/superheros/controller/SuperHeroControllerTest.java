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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;


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
        ).andExpect(jsonPath("$", hasSize(2))).andDo(print());
    }


}
