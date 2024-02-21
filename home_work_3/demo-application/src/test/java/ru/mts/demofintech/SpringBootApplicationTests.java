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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        Animal[] animals = animalRepository.findOlderAnimal(0);
        assertArrayEquals(animals, animalRepository.getAnimals());
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
