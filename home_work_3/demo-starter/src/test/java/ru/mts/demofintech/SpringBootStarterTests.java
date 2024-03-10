package ru.mts.demofintech;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {AnimalAutoConfiguration.class})
@ActiveProfiles("test")
public class SpringBootStarterTests {
    AnimalAutoConfiguration animalAutoConfiguration;
    @MockBean
    AnimalProperties animalProperties;
    AnimalConfig animalConfig;

    @BeforeEach
    void init() {
        animalAutoConfiguration = new AnimalAutoConfiguration(animalProperties);
    }

    @Test
    @DisplayName("animalConfig method via animalProperties test")
    public void testAnimalPropertiesWithAnimalConfigMethod() {
        when(animalProperties.getNameList()).thenReturn(List.of("Kotik", "Murka"));
        animalConfig = animalAutoConfiguration.animalConfig();
        assertEquals(animalConfig.get("name"), List.of("Kotik", "Murka"));
    }

    @Test
    @DisplayName("animalConfig method via nameList test")
    public void testNameListWithAnimalConfigMethod() {
        when(animalProperties.getNameList()).thenReturn(null);
        animalAutoConfiguration.setNameList(List.of("Kotik", "Murka"));
        animalConfig = animalAutoConfiguration.animalConfig();
        assertEquals(animalConfig.get("name"), List.of("Kotik", "Murka"));
    }

    @Test
    @DisplayName("getNameList method with NullPointerException test")
    public void testGetNameListNullPointerException() {
        when(animalProperties.getNameList()).thenReturn(null);
        assertThrows(NullPointerException.class, () -> {
            animalAutoConfiguration.getNameList();
        });
    }

    @Test
    @DisplayName("animalConfig method with null return test")
    public void testAnimalConfigNull() {
        when(animalProperties.getNameList()).thenReturn(null);
        assertNull(animalAutoConfiguration.animalConfig().get("name"));
    }

}
