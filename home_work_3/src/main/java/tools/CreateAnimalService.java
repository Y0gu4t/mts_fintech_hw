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
    default void createUniqueAnimals() {
        List<String> animalTypeList = Arrays.asList("fox", "cat", "fish", "bear");
        int i = 0;
        Random random = new Random();
        while (i <= 10) {
            Animal animal = null;
            String animalType = animalTypeList.get(random.nextInt(animalTypeList.size()));
            String breed = "Breed#" + random.nextInt();
            String name = "Name#" + random.nextInt();
            String character = "Character#" + random.nextInt();
            BigDecimal cost = BigDecimal.valueOf(random.nextDouble()).setScale(2, RoundingMode.HALF_UP);
            switch (animalType) {
                case "fox":
                    animal = new Fox(breed, name, cost, character);
                    break;
                case "cat":
                    animal = new Cat(breed, name, cost, character);
                    break;
                case "fish":
                    animal = new Fish(breed, name, cost, character);
                    break;
                case "bear":
                    animal = new Bear(breed, name, cost, character);
                    break;
            }
            i++;
            System.out.println(animal);
        }
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
    void createUniqueAnimals(int animalCount);
}
