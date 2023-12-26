package tools;

import agents.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public interface CreateAnimalService {
    /**
     * Creates unique animals and prints created animal's information (breed, name, cost and character).
     * The animal type is determined randomly from the list of animal types.
     * String attributes are defined as "attribute name" plus a random index.
     * The cost of the animal is determined using a random double value, which is scaled to 2 decimal places.
     *
     * @author y0gu4t
     */
    default Animal[] createUniqueAnimals() {
        Animal[] animals = new Animal[10];
        AnimalFactory animalFactory = new AnimalFactory();
        List<String> animalTypeList = Arrays.asList("fox", "cat", "fish", "bear");
        Random random = new Random();
        int i = 0;
        while (i < 10) {
            String animalType = animalTypeList.get(random.nextInt(animalTypeList.size()));
            animals[i] = animalFactory.createAnimal(animalType);
            System.out.println(animals[i]);
            i++;
        }
        return animals;
    }

    /**
     * Creates unique animals and prints created animal's information (breed, name, cost and character).
     * The animal type is determined randomly from the list of animal types.
     * String attributes are defined as "attribute name" plus a random index.
     * The cost of the animal is determined using a random double value, which is scaled to 2 decimal places.
     *
     * @param animalCount number of animals that will be created
     * @author y0gu4t
     */
    Animal[] createUniqueAnimals(int animalCount);
}
