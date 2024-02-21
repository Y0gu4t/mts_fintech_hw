package ru.mts.demofintech.tools;

import ru.mts.demofintech.AnimalConfig;
import ru.mts.demofintech.agents.Animal;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
    public Animal[] createUniqueAnimals() {
        Animal[] animals = new Animal[10];
        AnimalFactory animalFactory = new AnimalFactory(animalConfig);
        List<String> animalTypeList = Arrays.asList("fox", "cat", "fish", "bear");
        Random random = new Random();
        int i = 0;
        do {
            String animalType = animalTypeList.get(random.nextInt(animalTypeList.size()));
            animals[i] = animalFactory.createAnimal(animalType);
            System.out.println(animals[i]);
            i++;
        } while (i < 10);
        return animals;
    }

    @Override
    public Animal[] createUniqueAnimals(int animalCount) {
        Animal[] animals = new Animal[animalCount];
        AnimalFactory animalFactory = new AnimalFactory(animalConfig);
        List<String> animalTypeList = Arrays.asList("fox", "cat", "fish", "bear");
        Random random = new Random();
        for (int i = 0; i < animalCount; i++) {
            String animalType = animalTypeList.get(random.nextInt(animalTypeList.size()));
            animals[i] = animalFactory.createAnimal(animalType);
            System.out.println(animals[i]);
        }
        return animals;
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
