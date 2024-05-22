package ru.mts.demofintech.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import ru.mts.demofintech.Config;
import ru.mts.demofintech.dao.AnimalDao;
import ru.mts.demofintech.dao.AnimalTypeDao;
import ru.mts.demofintech.dao.BreedDao;
import ru.mts.demofintech.deserializer.AnimalDeserializer;
import ru.mts.demofintech.deserializer.AnimalKeyDeserializer;
import ru.mts.demofintech.entity.Animal;
import ru.mts.demofintech.repository.AnimalRepository;
import ru.mts.demofintech.repository.AnimalRepositoryImpl;
import ru.mts.demofintech.serializer.AnimalKeySerializer;
import ru.mts.demofintech.serializer.AnimalSerializer;
import ru.mts.demofintech.service.CreateAnimalService;
import ru.mts.demofintech.service.CreateAnimalServiceImpl;

@Configuration
public class AnimalConfiguration {
    @Bean
    @Scope(
            value = ConfigurableBeanFactory.SCOPE_PROTOTYPE,
            proxyMode = ScopedProxyMode.TARGET_CLASS)
    public CreateAnimalService createAnimalService(Config config) {
        return new CreateAnimalServiceImpl(config);
    }

    @Bean
    public AnimalRepository createAnimalRepository(CreateAnimalService createAnimalService, ObjectMapper objectMapper, AnimalDao animalDao, AnimalTypeDao animalTypeDao, BreedDao breedDao) {
        return new AnimalRepositoryImpl(createAnimalService, objectMapper, animalDao, animalTypeDao, breedDao);
    }

    @Bean
    @Primary
    public ObjectMapper createObjectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());
        SimpleModule animalModule = new SimpleModule();
        animalModule.addSerializer(Animal.class, new AnimalSerializer());
        animalModule.addDeserializer(Animal.class, new AnimalDeserializer());
        animalModule.addKeySerializer(Animal.class, new AnimalKeySerializer());
        animalModule.addKeyDeserializer(Animal.class, new AnimalKeyDeserializer(objectMapper));
        objectMapper.registerModule(animalModule);
        objectMapper.findAndRegisterModules();
        objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }
}
