package ru.mts.demofintech.tools;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mts.demofintech.AnimalConfig;
import ru.mts.demofintech.agents.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class AnimalFactory {
    /**
     * Creates an animal with an input type and a random breed, name and personality. Returns the created animal.
     *
     * @param type class of the animal being created
     * @return Animal
     * @author y0gu4t
     */
    private List<String> namesList;

    public AnimalFactory() {
    }

    @Autowired
    public AnimalFactory(AnimalConfig animalConfig) {
        namesList = animalConfig.get("name");
    }


    public Animal createAnimal(String type) {
        Random random = new Random();
        String breed = "Breed#" + random.nextInt(2);
        String name = namesList.get(random.nextInt(namesList.size()));
        String character = "Character#" + random.nextInt(2);
        LocalDate birthDate = LocalDate.of(random.nextInt(24) + 2000,
                random.nextInt(12) + 1,
                random.nextInt(28) + 1);
        BigDecimal cost = BigDecimal.valueOf(random.nextDouble()).setScale(2, RoundingMode.HALF_UP);
        switch (type) {
            case "Fox":
                return new Fox(breed, name, cost, character, birthDate);
            case "Cat":
                return new Cat(breed, name, cost, character, birthDate);
            case "Fish":
                return new Fish(breed, name, cost, character, birthDate);
            case "Bear":
                return new Bear(breed, name, cost, character, birthDate);
        }
        return null;
    }
}
