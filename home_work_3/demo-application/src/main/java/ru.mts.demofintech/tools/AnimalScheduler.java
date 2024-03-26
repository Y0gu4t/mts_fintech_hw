package ru.mts.demofintech.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.demofintech.exceptions.AnimalListOutOfBoundException;
import ru.mts.demofintech.exceptions.WrongYearException;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class AnimalScheduler {
    AnimalRepository animalRepository;
    ScheduledExecutorService executorService;

    @Autowired
    public AnimalScheduler(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @PostConstruct
    public void init() {
        executorService = Executors.newScheduledThreadPool(2);
        Runnable printDuplicatesRunnable = () -> {
            System.out.println("\nThread (printDuplicates):");
            animalRepository.printDuplicate();
        };
        Runnable findAverageAgeRunnable = () -> {
            System.out.println("\nThread (findAverageAge):");
            animalRepository.findAverageAge();
        };
        executorService.scheduleWithFixedDelay(printDuplicatesRunnable, 5, 5, TimeUnit.SECONDS);
        executorService.scheduleWithFixedDelay(findAverageAgeRunnable, 10, 10, TimeUnit.SECONDS);
    }

    @Scheduled(cron = "0 * * * * ?")
    public void callAnimalRepositoryMethods() {
        System.out.println("Calling methods on a class AnimalRepository:");
        System.out.println("\nAnimals that were born on a leap year:\n");
        animalRepository.findLeapYearNames().forEach((k, v) -> System.out.println(k + " " + v));
        System.out.println("\nAnimals that are older than 5 years:\n");
        try {
            animalRepository.findOlderAnimal(5).forEach((k, v) -> System.out.println(k + " " + v));
        } catch (WrongYearException e) {
            System.err.println("Catch the exception: " + e + "\n");
        }
        System.out.println("\nAnimals that are older than 5 years and are more expensive than average animals cost:\n");
        animalRepository.findOldAndExpensive().forEach(System.out::println);
        System.out.println("\nThe cheapest animal names:\n");
        try {
            System.out.println(animalRepository.findMinConstAnimals());
        } catch (AnimalListOutOfBoundException e) {
            System.err.println("Catch the exception: " + e + "\n");
        }
    }
}
