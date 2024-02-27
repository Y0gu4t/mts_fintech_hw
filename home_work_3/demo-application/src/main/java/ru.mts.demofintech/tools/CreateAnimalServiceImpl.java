package ru.mts.demofintech.tools;

import ru.mts.demofintech.AnimalConfig;
import ru.mts.demofintech.agents.Animal;

import java.util.*;

public class CreateAnimalServiceImpl implements CreateAnimalService {
    private AnimalConfig animalConfig;
    private String animalType;

    public CreateAnimalServiceImpl(AnimalConfig animalConfig) {
        this.animalConfig = animalConfig;
    }

    @Override
    public Animal createUniqueAnimal() {
        AnimalFactory animalFactory = new AnimalFactory(animalConfig);
        Animal animal = animalFactory.createAnimal(animalType);
        System.out.println(animal);
        return animal;
    }

    @Override
    public Map<String, List<Animal>> createUniqueAnimals() {
        Map<String, List<Animal>> animalsMap = new HashMap<>();
        AnimalFactory animalFactory = new AnimalFactory(animalConfig);
        List<String> animalTypeList = Arrays.asList("Fox", "Cat", "Fish", "Bear");
        for (String type:
                animalTypeList) {
            animalsMap.put(type, new ArrayList<>());
        }
        Random random = new Random();
        int i = 0;
        do {
            String animalType = animalTypeList.get(random.nextInt(animalTypeList.size()));
            Animal animal = animalFactory.createAnimal(animalType);
            animalsMap.get(animalType).add(animal);
            System.out.println(animal);
            i++;
        } while (i < 10);
        return animalsMap;
    }

    @Override
    public Map<String, List<Animal>> createUniqueAnimals(int animalCount) {
        Map<String, List<Animal>> animalsMap = new HashMap<>();
        AnimalFactory animalFactory = new AnimalFactory(animalConfig);
        List<String> animalTypeList = Arrays.asList("Fox", "Cat", "Fish", "Bear");
        for (String type:
                animalTypeList) {
            animalsMap.put(type, new ArrayList<>());
        }
        Random random = new Random();
        for (int i = 0; i < animalCount; i++) {
            String animalType = animalTypeList.get(random.nextInt(animalTypeList.size()));
            Animal animal = animalFactory.createAnimal(animalType);
            animalsMap.get(animalType).add(animal);
            System.out.println(animal);
        }
        return animalsMap;
    }

    public void defaultCreateUniqueAnimals() {
        CreateAnimalService.super.createUniqueAnimals();
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }
}
