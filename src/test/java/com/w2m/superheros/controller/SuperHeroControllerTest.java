package com.w2m.superheros.controller;

import com.w2m.superheros.domain.SuperHero;
import com.w2m.superheros.security.WebSecurity;
import com.w2m.superheros.service.SuperHeroService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = SuperHeroController.class)
@AutoConfigureMockMvc(addFilters = false)
public class SuperHeroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SuperHeroService superHeroService;

    private static final SuperHero superman = new SuperHero(1L, "Superman", "Clark Joseph Kent", "Metropolis");
    private static final SuperHero batman = new SuperHero(2L, "Batman","Bruce Wayne'","Ciudad Gotica");


    @Test
    void getAllSuperHerosWhenCallGetAllSuperherosEndpoint() throws Exception {
        when(superHeroService.findAll()).thenReturn(List.of(superman, batman));

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

        when(superHeroService.findById(1L)).thenReturn(Optional.of(superman));

        mockMvc.perform(MockMvcRequestBuilders.get("/superheros/1")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Superman"));
    }

    @Test
    void getErrorWhenCallGetSuperHeroByIdNotFound() throws Exception {

        when(superHeroService.findById(1L)).thenReturn(Optional.of(superman));

        mockMvc.perform(MockMvcRequestBuilders.get("/superheros/2")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("SuperHero not found with id 2"));
    }

    @Test
    void getSuperHerosWhenCallGetSuperHeroByName() throws Exception {

        when(superHeroService.findByName("man")).thenReturn(List.of(superman, batman));

        mockMvc.perform(MockMvcRequestBuilders.get("/superheros?name=man")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2))).andDo(print())
                .andExpect(jsonPath("$[0].name").value("Superman"))
                .andExpect(jsonPath("$[1].name").value("Batman"));
    }

    @Test
    void updateSomeDataOfASuperHero() throws Exception {

        SuperHero supermanUpdated = superman;
        supermanUpdated.setFullName("Clark Kent");
        supermanUpdated.setPlaceOfBirth("Cordoba");
        when(superHeroService.update(1L, supermanUpdated)).thenReturn((supermanUpdated));

        mockMvc.perform(MockMvcRequestBuilders.put("/superheros/1")
                .content("{\n" +
                        "    \"id\": 1,\n" +
                        "    \"name\": \"Superman\",\n" +
                        "    \"fullName\": \"Clark Kent\",\n" +
                        "    \"placeOfBirth\": \"Cordoba\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Superman"))
                .andExpect(jsonPath("$.fullName").value("Clark Kent"))
                .andExpect(jsonPath("$.placeOfBirth").value("Cordoba"));

    }

    @Test
    void deleteASuperHeroById() throws Exception {

        Mockito.doNothing().when(superHeroService).delete(1L);
        mockMvc.perform(MockMvcRequestBuilders.delete("/superheros/1")
        )
                .andExpect(status().isNoContent());
    }


}
