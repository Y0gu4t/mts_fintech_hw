package ru.mts.demofintech.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.*;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import ru.mts.demofintech.deserializer.AnimalDeserializer;
import ru.mts.demofintech.deserializer.AnimalKeyDeserializer;
import ru.mts.demofintech.entity.Animal;
import ru.mts.demofintech.serializer.AnimalKeySerializer;
import ru.mts.demofintech.serializer.AnimalSerializer;

@Configuration
public class AnimalConfiguration {
    /*@Bean
    public LocalSessionFactoryBean entityManagerFactory() {
        return new LocalSessionFactoryBean();
    }*/

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
