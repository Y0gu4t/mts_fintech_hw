package ru.mts.demofintech.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AnimalScheduler {
    AnimalRepository animalRepository;

    @Autowired
    public AnimalScheduler(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Scheduled(cron = "*/2 * * * * ?")
    public void callAnimalRepositoryMethods() {
        System.out.println("Calling methods on a class AnimalRepository:");
        animalRepository.printDuplicate();
        System.out.println("\nAnimals that were born on a leap year:\n");
        animalRepository.findLeapYearNames().forEach((k, v) -> System.out.println(k + " " + v));
        System.out.println("\nAnimals that are older than 5 years:\n");
        animalRepository.findOlderAnimal(5).forEach((k, v) -> System.out.println(k + " " + v));
        animalRepository.findAverageAge();
        System.out.println("\nAnimals that are older than 5 years and are more expensive than average animals cost:\n");
        animalRepository.findOldAndExpensive().forEach(System.out::println);
        System.out.println("\nThe cheapest animal names:\n");
        System.out.println(animalRepository.findMinConstAnimals());
    }
}
