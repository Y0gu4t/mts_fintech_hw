package ru.mts.demofintech;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import ru.mts.demofintech.controller.AnimalRestController;
import ru.mts.demofintech.entity.Animal;
import ru.mts.demofintech.repository.AnimalRepository;
import ru.mts.demofintech.service.AnimalService;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@WebMvcTest(AnimalRestController.class)
@AutoConfigureMockMvc
public class AnimalControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AnimalRepository animalRepository;
    @MockBean
    private AnimalService animalService;

    @Test
    @DisplayName("GET /animals test")
    void testAnimalList() throws Exception {
        List<Animal> animalList = List.of(Animal.builder().name("1").build(),
                Animal.builder().name("2").build());

        Mockito.when(animalRepository.findAll()).thenReturn(animalList);
        mockMvc.perform(get("/animals/rest")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Matchers.is(2)));
    }

    @Test
    @DisplayName("POST /animals/rest/add test")
    void testAddAnimal() throws Exception {
        mockMvc.perform(post("/animals/rest/add")
                        .content("{\n" +
                                "    \"name\" : \"Test1\",\n" +
                                "    \"character\" : \"Char1\",\n" +
                                "    \"birthday\" : \"2001-01-01\",\n" +
                                "    \"breedId\" : 1,\n" +
                                "    \"animaTypeId\" : 1\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("OK"));
    }

    @Test
    @DisplayName("DELETE /animals/rest/delete/{id} test")
    void testDeleteAnimalById() throws Exception {
        Long id = 1L;
        mockMvc.perform(delete("/animals/rest/delete/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("OK"));
        Mockito.verify(animalRepository, Mockito.times(1)).deleteById(id);
    }
}
