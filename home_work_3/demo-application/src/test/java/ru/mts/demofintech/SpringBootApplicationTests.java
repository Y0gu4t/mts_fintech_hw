package ru.mts.demofintech;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.demofintech.agents.Animal;
import ru.mts.demofintech.config.AnimalRepositoryTestConfiguration;
import ru.mts.demofintech.tools.AnimalRepository;
import ru.mts.demofintech.tools.AnimalTypeBeanPostProcessor;
import ru.mts.demofintech.tools.CreateAnimalService;
import ru.mts.demofintech.tools.CreateAnimalServiceImpl;

import java.time.LocalDate;
import java.time.Period;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {AnimalRepositoryTestConfiguration.class})
@ActiveProfiles("test")
public class SpringBootApplicationTests {
    @Autowired
    AnimalRepository animalRepository;
    @Autowired
    CreateAnimalService createAnimalService;
    @Autowired
    AnimalConfig animalConfig;
    @Autowired
    AnimalTypeBeanPostProcessor animalTypeBeanPostProcessor;

    @Test
    @DisplayName("The creation of an animal with a name from animalConfig test")
    public void testAnimalCreationWithCorrectName() {
        Animal animal = createAnimalService.createUniqueAnimal();
        assertThat(animalConfig.get("name")).contains(animal.getName());
    }

    @Test
    @DisplayName("The findOlderAnimal method with the parameter 0 test")
    public void testFindOlderAnimalMethodWithParameter0() {
        Map<Animal, Integer> olderAnimalMap = animalRepository.findOlderAnimal(0);
        assertTrue(animalRepository.getAnimals().containsAll(olderAnimalMap.keySet()));
    }

    @Test
    @DisplayName("The findOlderAnimal method when there are no animals older than a given age test")
    public void testNoOlderAnimal() {
        int greatestAge = 0;
        for (Animal animal: animalRepository.getAnimals()) {
            int animalAge = Period.between(animal.getBirthDate(),LocalDate.now()).getYears();
            if (animalAge > greatestAge) {
                greatestAge = animalAge;
            }
        }
        Map<Animal, Integer> animalMap = animalRepository.findOlderAnimal(1000);
        assertEquals(animalMap.size(), 1);
        assertTrue(animalMap.containsValue(greatestAge));
    }
    
    @Test
    @DisplayName("The createUniqueAnimal method with null animalConfig test")
    public void testCreateUniqueAnimalWithNullAnimalConfig() {
        createAnimalService = new CreateAnimalServiceImpl(null);
        assertThrows(NullPointerException.class, () ->{
            createAnimalService.createUniqueAnimal();
        });
    }

    @Test
    @DisplayName("The postProcessBeforeInitialization with proxy test")
    public void testPostProcessBeforeInitializationWithProxy() {
        assertThrows(ClassCastException.class, () -> {
            animalTypeBeanPostProcessor.postProcessBeforeInitialization(createAnimalService, "test");
        });
    }
}
