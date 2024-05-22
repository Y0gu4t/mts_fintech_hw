package ru.mts.demofintech.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import ru.mts.demofintech.dao.AnimalTypeDao;
import ru.mts.demofintech.service.CreateAnimalService;
import ru.mts.demofintech.service.CreateAnimalServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Configuration
public class AnimalTypeBeanPostProcessor implements BeanPostProcessor {

    /*@Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof CreateAnimalService) {
            Random random = new Random();
            List<String> animalTypeList = Arrays.asList("Fox", "Cat", "Fish", "Bear");
            CreateAnimalServiceImpl createAnimalService = (CreateAnimalServiceImpl) bean;
            createAnimalService.setAnimalType(animalTypeList.get(random.nextInt(animalTypeList.size())));
        }
        return bean;
    }*/
}
