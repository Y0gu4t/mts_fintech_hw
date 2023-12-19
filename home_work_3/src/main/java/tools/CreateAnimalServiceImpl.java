package tools;

import agents.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CreateAnimalServiceImpl implements CreateAnimalService {

    @Override
    public void createUniqueAnimals() {
        int i = 0;
        do {
            Animal animal = createUniqueAnimal();
            i++;
            System.out.println(animal);
        } while (i < 10);
    }

    @Override
    public void createUniqueAnimals(int animalCount) {
        for (int i = 0; i < animalCount; i++) {
            Animal animal = createUniqueAnimal();
            System.out.println(animal);
        }
    }

    public void defaultCreateUniqueAnimals() {
        CreateAnimalService.super.createUniqueAnimals();
    }

    private Animal createUniqueAnimal() {
        List<String> animalTypeList = Arrays.asList("fox", "cat", "fish", "bear");
        Random random = new Random();
        String animalType = animalTypeList.get(random.nextInt(animalTypeList.size()));
        String breed = "Breed#" + random.nextInt();
        String name = "Name#" + random.nextInt();
        String character = "Character#" + random.nextInt();
        BigDecimal cost = BigDecimal.valueOf(random.nextDouble()).setScale(2, RoundingMode.HALF_UP);
        switch (animalType) {
            case "fox":
                return new Fox(breed, name, cost, character);
            case "cat":
                return new Cat(breed, name, cost, character);
            case "fish":
                return new Fish(breed, name, cost, character);
            case "bear":
                return new Bear(breed, name, cost, character);
        }
        return null;
    }
}
