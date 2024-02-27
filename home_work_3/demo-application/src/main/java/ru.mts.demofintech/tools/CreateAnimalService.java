package ru.mts.demofintech.tools;

import ru.mts.demofintech.agents.Animal;

import java.util.*;

public interface CreateAnimalService {
    /**
     * Creates unique animals and prints created animal's information (breed, name, cost and character).
     * The animal type is determined randomly from the list of animal types.
     * String attributes are defined as "attribute name" plus a random index.
     * The cost of the animal is determined using a random double value, which is scaled to 2 decimal places.
     *
     * @author y0gu4t
     */
    default Map<String, List<Animal>> createUniqueAnimals() {
        Map<String, List<Animal>> animalsMap = new HashMap<>();
        AnimalFactory animalFactory = new AnimalFactory();
        List<String> animalTypeList = Arrays.asList("Fox", "Cat", "Fish", "Bear");
        for (String type:
             animalTypeList) {
            animalsMap.put(type, new ArrayList<>());
        }
        Random random = new Random();
        int i = 0;
        while (i < 10) {
            String animalType = animalTypeList.get(random.nextInt(animalTypeList.size()));
            Animal animal = animalFactory.createAnimal(animalType);
            animalsMap.get("fish").add(animal);
            System.out.println(animal);
            i++;
        }
        return animalsMap;
    }

    /**
     * Creates unique animal and prints created animal's information (breed, name, cost and character).
     * The animal type is determined randomly from the list of animal types.
     * String attributes are defined as "attribute name" plus a random index.
     * The cost of the animal is determined using a random double value, which is scaled to 2 decimal places.
     *
     * @author y0gu4t
     */
    Animal createUniqueAnimal();

    /**
     * Creates unique animals and prints created animal's information (breed, name, cost and character).
     * The animal type is determined randomly from the list of animal types.
     * String attributes are defined as "attribute name" plus a random index.
     * The cost of the animal is determined using a random double value, which is scaled to 2 decimal places.
     *
     * @param animalCount number of animals that will be created
     * @author y0gu4t
     */
    Map<String, List<Animal>> createUniqueAnimals(int animalCount);
}
