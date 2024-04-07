package ru.mts.demofintech.tools;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.demofintech.agents.Animal;
import ru.mts.demofintech.exceptions.AnimalListOutOfBoundException;
import ru.mts.demofintech.exceptions.WrongYearException;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class AnimalScheduler {
    AnimalRepository animalRepository;
    ScheduledExecutorService executorService;
    @Value("${application.results}")
    private Path resultsPath;
    private ObjectMapper objectMapper;

    @Autowired
    public AnimalScheduler(AnimalRepository animalRepository, ObjectMapper objectMapper) {
        this.animalRepository = animalRepository;
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void init() {
        executorService = Executors.newScheduledThreadPool(2);
        Runnable printDuplicatesRunnable = () -> {
            System.out.println("\nThread (printDuplicates):");
            animalRepository.findDuplicate();
            TypeReference<Map<String, List<Animal>>> findDuplicateTypeRef = new TypeReference<>() {
            };
            readResultsFromFile("findDuplicate.json", findDuplicateTypeRef);
        };
        Runnable findAverageAgeRunnable = () -> {
            System.out.println("\nThread (findAverageAge):");
            animalRepository.findAverageAge();
            readResultsFromFile("findAverageAge.json", Double.class);
        };
        executorService.scheduleWithFixedDelay(printDuplicatesRunnable, 5, 5, TimeUnit.SECONDS);
        executorService.scheduleWithFixedDelay(findAverageAgeRunnable, 10, 10, TimeUnit.SECONDS);
    }

    @Scheduled(cron = "0 * * * * ?")
    public void callAnimalRepositoryMethods() {
        System.out.println("Calling methods on a class AnimalRepository:");
        System.out.println("\nAnimals that were born on a leap year:\n");
        animalRepository.findLeapYearNames();
        TypeReference<Map<String, LocalDate>> findLeapYearNamesTypeRef = new TypeReference<>() {
        };
        readResultsFromFile("findLeapYearNames.json", findLeapYearNamesTypeRef);
        System.out.println("\nAnimals that are older than 5 years:\n");
        try {
            animalRepository.findOlderAnimal(5);
            TypeReference<Map<Animal, Integer>> findOlderAnimalTypeRef = new TypeReference<>() {
            };
            readResultsFromFile("findOlderAnimal.json", findOlderAnimalTypeRef);
        } catch (WrongYearException e) {
            System.err.println("Catch the exception: " + e + "\n");
        }
        System.out.println("\nAnimals that are older than 5 years and are more expensive than average animals cost:\n");
        animalRepository.findOldAndExpensive();
        TypeReference<List<Animal>> findOldAndExpensiveTypeRef = new TypeReference<>() {
        };
        readResultsFromFile("findOldAndExpensive.json", findOldAndExpensiveTypeRef);
        System.out.println("\nThe cheapest animal names:\n");
        try {
            animalRepository.findMinConstAnimals();
            TypeReference<List<String>> findMinConstAnimalsTypeRef = new TypeReference<>() {
            };
            readResultsFromFile("findMinConstAnimals.json", findMinConstAnimalsTypeRef);
        } catch (AnimalListOutOfBoundException e) {
            System.err.println("Catch the exception: " + e + "\n");
        }
    }

    private <T> void readResultsFromFile(String fileName, TypeReference<T> typeReference) {
        Path filePath = resultsPath.resolve(fileName);
        if (Files.exists(filePath)) {
            try {
                System.out.printf("%s: \n %s\n",
                        fileName,
                        objectMapper.readValue(filePath.toFile(), typeReference));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private <T> void readResultsFromFile(String fileName, Class<T> tClass) {
        Path filePath = resultsPath.resolve(fileName);
        if (Files.exists(filePath)) {
            try {
                System.out.printf("%s: \n %s\n",
                        fileName,
                        objectMapper.readValue(filePath.toFile(), tClass));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
