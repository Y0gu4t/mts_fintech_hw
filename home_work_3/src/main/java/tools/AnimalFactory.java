package tools;

import agents.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Random;

public class AnimalFactory {
    /**
     * Creates an animal with an input type and a random breed, name and personality. Returns the created animal.
     * @param type class of the animal being created
     * @return Animal
     * @author y0gu4t
     * */
    public Animal createAnimal(String type) {
        Random random = new Random();
        String breed = "Breed#" + random.nextInt(2);
        String name;
        String character = "Character#" + random.nextInt(2);
        LocalDate birthDate = LocalDate.of(random.nextInt(24) + 2000,
                random.nextInt(12) + 1,
                random.nextInt(28) + 1);
        BigDecimal cost = BigDecimal.valueOf(random.nextDouble()).setScale(2, RoundingMode.HALF_UP);
        switch (type) {
            case "fox":
                name = "Fox#" + random.nextInt(3);
                return new Fox(breed, name, cost, character, birthDate);
            case "cat":
                name = "Cat#" + random.nextInt(3);
                return new Cat(breed, name, cost, character, birthDate);
            case "fish":
                name = "Fish#" + random.nextInt(3);
                return new Fish(breed, name, cost, character, birthDate);
            case "bear":
                name = "Bear#" + random.nextInt(3);
                return new Bear(breed, name, cost, character, birthDate);
        }
        return null;
    }
}
