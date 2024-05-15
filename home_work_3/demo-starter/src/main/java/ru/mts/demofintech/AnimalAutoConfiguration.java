package ru.mts.demofintech;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;


@Configuration
@RequiredArgsConstructor
@Getter
@Setter
@ConditionalOnClass(Config.class)
@EnableConfigurationProperties({AnimalProperties.class, HabitatProperties.class, ProviderProperties.class})
public class AnimalAutoConfiguration {
    private final AnimalProperties animalProperties;
    private final HabitatProperties habitatProperties;
    private final ProviderProperties providerProperties;

    @Value("${application.animal.name}")
    private List<String> animalNameList;
    @Value("${application.animal.type}")
    private List<String> animalTypeList;
    @Value("${application.animal.breed}")
    private List<String> animalBreedList;
    @Value("${application.habitat.area}")
    private List<String> habitatAreaList;
    @Value("${application.provider.name}")
    private List<String> providerNameList;
    @Value("${application.provider.phone}")
    private List<String> providerPhoneList;
    @Bean
    @ConditionalOnMissingBean
    public Config config() {
        Config config = new Config();

        List<String> animalNames= animalProperties.getNameList() == null ? animalNameList :
                animalProperties.getNameList();
        List<String> animalTypes= animalProperties.getNameList() == null ? animalTypeList :
                animalProperties.getTypeList();
        List<String> animalBreeds = animalProperties.getBreedList() == null ? animalBreedList :
                animalProperties.getBreedList();
        HashMap<String, List<String>> animalMap = new HashMap<>();
        animalMap.put("name", animalNames);
        animalMap.put("type", animalTypes);
        animalMap.put("breed", animalBreeds);

        List<String> habitatAreas = habitatProperties.getAreaList() == null ? habitatAreaList :
                habitatProperties.getAreaList();
        HashMap<String, List<String>> habitatMap = new HashMap<>();
        habitatMap.put("area", habitatAreas);

        List<String> providerNames = providerProperties.getNameList() == null ? providerNameList :
                providerProperties.getNameList();
        List<String> providerPhones = providerProperties.getPhoneList() == null ? providerPhoneList :
                providerProperties.getPhoneList();
        HashMap<String, List<String>> providerMap = new HashMap<>();
        providerMap.put("name", providerNames);
        providerMap.put("phone", providerPhones);

        config.put("animal", animalMap);
        config.put("habitat", habitatMap);
        config.put("provider", providerMap);
        return config;
    }
}
