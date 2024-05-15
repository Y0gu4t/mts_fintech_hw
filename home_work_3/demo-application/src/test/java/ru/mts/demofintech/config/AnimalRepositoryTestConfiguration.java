/*
package ru.mts.demofintech.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import ru.mts.demofintech.repository.AnimalRepository;
import ru.mts.demofintech.repository.AnimalRepositoryImpl;
import ru.mts.demofintech.service.CreateAnimalService;

@TestConfiguration
public class AnimalRepositoryTestConfiguration {
    @Bean
    public AnimalRepository animalRepository(CreateAnimalService createAnimalService, ObjectMapper objectMapper) {
        return new AnimalRepositoryImpl(createAnimalService, objectMapper);
    }
}
*/
