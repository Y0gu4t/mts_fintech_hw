package ru.mts.demofintech.bpp;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

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
