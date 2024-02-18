package tools;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Configuration
public class AnimalConfiguration {
    @Bean
    public AnimalRepository createAnimalRepository(CreateAnimalService createAnimalService) {
        return new AnimalRepositoryImpl(createAnimalService);
    }

    @Bean
    @Scope(
            value = ConfigurableBeanFactory.SCOPE_PROTOTYPE,
            proxyMode = ScopedProxyMode.TARGET_CLASS)
    public CreateAnimalService createAnimalService() {
        return new CreateAnimalServiceImpl();
    }
}
