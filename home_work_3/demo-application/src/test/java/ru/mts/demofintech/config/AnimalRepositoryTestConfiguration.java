package ru.mts.demofintech.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import ru.mts.demofintech.tools.AnimalRepository;
import ru.mts.demofintech.tools.AnimalRepositoryImpl;
import ru.mts.demofintech.tools.CreateAnimalService;

@TestConfiguration
public class AnimalRepositoryTestConfiguration {
    @Bean
    public AnimalRepository animalRepository(CreateAnimalService createAnimalService) {
        return new AnimalRepositoryImpl(createAnimalService);
    }
}
