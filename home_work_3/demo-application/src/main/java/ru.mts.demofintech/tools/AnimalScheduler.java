package ru.mts.demofintech.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.demofintech.agents.Animal;

@Component
public class AnimalScheduler {
    AnimalRepository animalRepository;

    @Autowired
    public AnimalScheduler(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Scheduled(cron = "0 * * * * ?")
    public void callAnimalRepositoryMethods() {
        System.out.println("Calling methods on a class AnimalRepository:");
        animalRepository.printDuplicate();
        System.out.println("\nAnimals that were born on a leap year:\n");
        for (String name :
                animalRepository.findLeapYearNames()) {
            System.out.println(name);
        }
        System.out.println("\nAnimals that are older than 5 years:\n");
        for (Animal animal :
                animalRepository.findOlderAnimal(5)) {
            System.out.println(animal);
        }
    }
}
