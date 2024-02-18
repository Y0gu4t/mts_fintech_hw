package tools;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AnimalConfiguration {
    @Bean
    public AnimalRepository animalRepository() {
        return new AnimalRepositoryImpl(this);
    }

    @Bean
    @Scope(scopeName = "prototype")
    public CreateAnimalService createAnimalService() {
        return new CreateAnimalServiceImpl();
    }
}
