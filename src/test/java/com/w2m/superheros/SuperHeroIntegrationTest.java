package com.w2m.superheros;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Sql(executionPhase= Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts="classpath:/insert-test-data.sql")
@Sql(executionPhase= Sql.ExecutionPhase.AFTER_TEST_METHOD,statements="DELETE FROM SUPERHERO")
public class SuperHeroIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllSuperHerosWhenCallGetAllSuperherosEndpoint() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/superheros")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3))).andDo(print())
                .andExpect(jsonPath("$[0].name").value("Superman"))
                .andExpect(jsonPath("$[1].name").value("Batman"))
                .andExpect(jsonPath("$[2].name").value("Thor"));
    }

    @Test
    void getASuperHeroWhenCallGetSuperHeroById() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/superheros/1")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Superman"));
    }

    @Test
    void getErrorWhenCallGetSuperHeroByIdNotFound() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders.get("/superheros/6")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("SuperHero not found with id 6"));
    }

    @Test
    void getSuperHerosWhenCallGetSuperHeroByName() throws Exception {

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

        mockMvc.perform(MockMvcRequestBuilders.delete("/superheros/1")
        )
                .andExpect(status().isNoContent());
    }

    @Test
    void getErrorWhenCallDeleteSuperHeroByIdNotFound() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders.delete("/superheros/6")
        )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("SuperHero not found with id 6"));
    }


}
