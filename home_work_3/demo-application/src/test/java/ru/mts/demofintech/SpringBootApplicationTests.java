package ru.mts.demofintech;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.demofintech.agents.Animal;
import ru.mts.demofintech.agents.Bear;
import ru.mts.demofintech.config.AnimalRepositoryTestConfiguration;
import ru.mts.demofintech.tools.AnimalRepository;
import ru.mts.demofintech.tools.AnimalTypeBeanPostProcessor;
import ru.mts.demofintech.tools.CreateAnimalService;
import ru.mts.demofintech.tools.CreateAnimalServiceImpl;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
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
    @DisplayName("The findOldAndExpensive method test")
    public void testFindOldAndExpensive() {
        List<Animal> oldAnimalList = animalRepository.findOldAndExpensive();
        BigDecimal averageCost = animalRepository.getAnimals().stream()
                        .map(Animal::getCost)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                        .divide(BigDecimal.valueOf(animalRepository.getAnimals().size()));

        oldAnimalList.forEach(animal -> {
            assertTrue(Period.between(animal.getBirthDate(), LocalDate.now()).getYears() >= 5);
            assertEquals(1, animal.getCost().compareTo(averageCost));
        });
        for (int i = 1; i < oldAnimalList.size(); i++) {
            Animal animal1 = oldAnimalList.get(i-1);
            Animal animal2 = oldAnimalList.get(i);
            assertTrue(animal2.getBirthDate().isAfter(animal1.getBirthDate()));
        }
    }

    @Test
    @DisplayName("The findMinConstAnimals method with replaced list of animals test")
    public void testFindMinConstAnimals() throws NoSuchFieldException, IllegalAccessException {
        List<Animal> animals = List.of(
                new Bear("Breed#0", "Test3", BigDecimal.valueOf(0.5), "Character#0", LocalDate.now()),
                new Bear("Breed#1", "Test1", BigDecimal.valueOf(0.1), "Character#1", LocalDate.now()),
                new Bear("Breed#2", "Test2", BigDecimal.valueOf(0.2), "Character#2", LocalDate.now()),
                new Bear("Breed#3", "Test4", BigDecimal.valueOf(1.0), "Character#3", LocalDate.now())
        );
        Field animalField = animalRepository.getClass().getDeclaredField("animals");
        animalField.setAccessible(true);
        animalField.set(animalRepository, animals);
        List<String> nameList = animalRepository.findMinConstAnimals();
        List<String> expectedList = List.of("Test3", "Test2", "Test1");
        assertEquals(3, nameList.size());
        for (int i = 0; i < nameList.size(); i++) {
            assertEquals(expectedList.get(i), nameList.get(i));
        }
    }

    @Test
    @DisplayName("The getAnimals method test")
    public void testGetAnimals() throws NoSuchFieldException, IllegalAccessException {
        List<Animal> animals = List.of(
                new Bear("Breed#0", "Test3", BigDecimal.valueOf(0.5), "Character#0", LocalDate.now()),
                new Bear("Breed#1", "Test1", BigDecimal.valueOf(0.1), "Character#1", LocalDate.now()),
                new Bear("Breed#2", "Test2", BigDecimal.valueOf(0.2), "Character#2", LocalDate.now()),
                new Bear("Breed#3", "Test4", BigDecimal.valueOf(1.0), "Character#3", LocalDate.now())
        );
        Field animalField = animalRepository.getClass().getDeclaredField("animals");
        animalField.setAccessible(true);
        animalField.set(animalRepository, animals);
        List<Animal> nameList = animalRepository.getAnimals();
        assertEquals(animals, nameList);
    }

    @Test
    @DisplayName("The postProcessBeforeInitialization with proxy test")
    public void testPostProcessBeforeInitializationWithProxy() {
        assertThrows(ClassCastException.class, () -> {
            animalTypeBeanPostProcessor.postProcessBeforeInitialization(createAnimalService, "test");
        });
    }
}
