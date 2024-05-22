package ru.mts.demofintech.tools;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import ru.mts.demofintech.AnimalConfig;
import ru.mts.demofintech.agents.AbstractAnimal;
import ru.mts.demofintech.agents.Animal;
import ru.mts.demofintech.deserializer.AnimalDeserializer;
import ru.mts.demofintech.deserializer.AnimalKeyDeserializer;
import ru.mts.demofintech.serializer.AnimalKeySerializer;
import ru.mts.demofintech.serializer.AnimalSerializer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
public class AnimalConfiguration {

    @Value("${application.threadPoolSize}")
    private int threadPoolSize;
    @Bean
    public AnimalRepository createAnimalRepository(CreateAnimalService createAnimalService, ObjectMapper objectMapper) {
        return new AnimalRepositoryImpl(createAnimalService, objectMapper);
    }

    @Bean
    public ScheduledExecutorService executorService() {
        return Executors.newScheduledThreadPool(threadPoolSize);
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

    @Bean
    @Scope(
            value = ConfigurableBeanFactory.SCOPE_PROTOTYPE,
            proxyMode = ScopedProxyMode.TARGET_CLASS)
    public CreateAnimalService createAnimalService(AnimalConfig animalConfig) {
        return new CreateAnimalServiceImpl(animalConfig);
    }
}
