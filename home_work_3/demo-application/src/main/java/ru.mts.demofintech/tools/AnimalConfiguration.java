package ru.mts.demofintech.tools;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import ru.mts.demofintech.AnimalConfig;

import java.text.SimpleDateFormat;

@Configuration
public class AnimalConfiguration {
    @Bean
    public AnimalRepository createAnimalRepository(CreateAnimalService createAnimalService, ObjectMapper objectMapper) {
        return new AnimalRepositoryImpl(createAnimalService, objectMapper);
    }

    @Bean
    @Primary
    public ObjectMapper createObjectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    @Bean
    @Scope(
            value = ConfigurableBeanFactory.SCOPE_PROTOTYPE,
            proxyMode = ScopedProxyMode.TARGET_CLASS)
    public CreateAnimalService createAnimalService(AnimalConfig animalConfig) {
        return new CreateAnimalServiceImpl(animalConfig);
    }
}
