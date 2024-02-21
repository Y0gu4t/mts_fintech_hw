package ru.mts.demofintech;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;


@Configuration
@ConditionalOnClass(AnimalConfig.class)
@EnableConfigurationProperties(AnimalProperties.class)
public class AnimalAutoConfiguration {
    private AnimalProperties animalProperties;

    public AnimalAutoConfiguration(AnimalProperties animalProperties) {
        this.animalProperties = animalProperties;
    }

    @Value("${application.animal.name}")
    private List<String> nameList;

    @Bean
    @ConditionalOnMissingBean
    public AnimalConfig animalConfig() {
        AnimalConfig animalConfig = new AnimalConfig();
        List<String> list = animalProperties.getNameList() == null ? nameList :
                animalProperties.getNameList();
        animalConfig.put("name", list);
        return animalConfig;
    }

    public List<String> getNameList() {
        return new ArrayList<>(nameList);
    }

    public void setNameList(List<String> nameList) {
        this.nameList = nameList;
    }
}