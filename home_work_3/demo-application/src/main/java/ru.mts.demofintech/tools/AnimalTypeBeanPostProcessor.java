package ru.mts.demofintech.tools;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Configuration
public class AnimalTypeBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof CreateAnimalService) {
            Random random = new Random();
            List<String> animalTypeList = Arrays.asList("fox", "cat", "fish", "bear");
            CreateAnimalServiceImpl createAnimalService = (CreateAnimalServiceImpl) bean;
            createAnimalService.setAnimalType(animalTypeList.get(random.nextInt(animalTypeList.size())));
        }
        return bean;
    }
}
