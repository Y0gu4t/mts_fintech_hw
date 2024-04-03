package ru.mts.demofintech.tools;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import ru.mts.demofintech.agents.Animal;
import ru.mts.demofintech.exceptions.AnimalListOutOfBoundException;
import ru.mts.demofintech.exceptions.WrongYearException;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class AnimalRepositoryImpl implements AnimalRepository {
    CreateAnimalService createAnimalService;
    ObjectMapper objectMapper;
    private final List<Animal> animals;
    @Value("${application.results}")
    private Path resultsPath;

    public AnimalRepositoryImpl(CreateAnimalService createAnimalService, ObjectMapper objectMapper) {
        this.createAnimalService = createAnimalService;
        this.objectMapper = objectMapper;
        animals = new CopyOnWriteArrayList<>();
    }

    @PostConstruct
    public void createAnimals() throws FileNotFoundException {
        System.out.println();
        for (int i = 0; i < 40; i++) {
            animals.add(createAnimalService.createUniqueAnimal());
        }
        System.out.println();
    }

    @Override
    public Map<String, LocalDate> findLeapYearNames() {
        Map<String, LocalDate> result = animals.stream()
                .filter(animal -> animal.getBirthDate().getYear() % 4 == 0)
                .filter(animal -> animal.getBirthDate().getYear() % 100 != 0 || animal.getBirthDate().getYear() % 400 == 0)
                .collect(Collectors.toConcurrentMap(
                        animal -> String.format("%s %s", animal.getType(), animal.getName()),
                        Animal::getBirthDate,
                        (animal1, animal2) -> animal1));
        writeMethodResultToJson("findLeapYearNames.json", result);
        return result;
    }

    @Override
    public Map<Animal, Integer> findOlderAnimal(int years) throws WrongYearException {
        if (years < 0) {
            throw new WrongYearException("Negative age indicated");
        }
        Map<Animal, Integer> olderAnimalMap = animals.stream()
                .filter(animal -> LocalDate.now().minusYears(years).isAfter(animal.getBirthDate()))
                .collect(Collectors.toConcurrentMap(
                        animal -> animal,
                        animal -> Period.between(animal.getBirthDate(), LocalDate.now()).getYears()));

        if (olderAnimalMap.isEmpty()) {
            return animals.stream()
                    .min(Comparator.comparing(Animal::getBirthDate))
                    .map(animal -> Map.of(animal, Period.between(animal.getBirthDate(), LocalDate.now()).getYears()))
                    .orElse(Collections.emptyMap());
        }

        writeMethodResultToJson("findOlderAnimal.json", olderAnimalMap);

        return olderAnimalMap;
    }

    @Override
    public Map<String, List<Animal>> findDuplicate() {
        Map<String, List<Animal>> result = animals.stream()
                .collect(Collectors.groupingBy(Animal::getType,
                        Collectors.collectingAndThen(Collectors.toList(),
                                list -> list.stream()
                                        .filter(animal -> list.stream()
                                                .filter(a -> a.equals(animal)).count() > 1)
                                        .collect(Collectors.collectingAndThen(Collectors.toList(), CopyOnWriteArrayList::new)))));
        writeMethodResultToJson("findDuplicate.json", result);
        return result;
    }

    @Override
    public void printDuplicate() {
        System.out.println("Animal Duplicates:");
        findDuplicate().forEach((k, v) -> System.out.println(k + " = " + v));
        System.out.println();
    }

    @Override
    public void findAverageAge() {
        double result = animals.stream()
                .mapToInt(animal -> Period.between(animal.getBirthDate(), LocalDate.now()).getYears())
                .average()
                .orElseThrow(() -> new RuntimeException("Ошибка при подсчёте среднего возраста"));
        System.out.printf("Average animal age: %.2f\n",result);
        writeMethodResultToJson("findAverageAge.json", result);
    }

    @Override
    public List<Animal> findOldAndExpensive() {
        BigDecimal averageAnimalCost = animals.stream()
                .map(Animal::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(animals.size()));

        List<Animal> result = animals.stream()
                .filter(animal -> LocalDate.now().minusYears(5).isAfter(animal.getBirthDate()))
                .filter(animal -> animal.getCost().compareTo(averageAnimalCost) == 1)
                .sorted(Comparator.comparing(Animal::getBirthDate))
                .collect(Collectors.collectingAndThen(Collectors.toList(), CopyOnWriteArrayList::new));

        writeMethodResultToJson("findOldAndExpensive.json", result);

        return result;
    }

    @Override
    public List<String> findMinConstAnimals() throws AnimalListOutOfBoundException {
        int minConstAnimalsListSize = 3;
        if (animals.size() < minConstAnimalsListSize) {
            throw new AnimalListOutOfBoundException(
                    String.format("List size is %d, expected array size greater than or equal to %d",
                            animals.size(),
                            minConstAnimalsListSize));
        }
        List<String> result = animals.stream()
                .sorted(Comparator.comparing(Animal::getCost))
                .limit(minConstAnimalsListSize)
                .map(Animal::getName)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.collectingAndThen(Collectors.toList(), CopyOnWriteArrayList::new));
        writeMethodResultToJson("findMinConstAnimals.json", result);
        return result;
    }

    @Override
    public void writeMethodResultToJson(String fileName, Object object) {
        Path filePath = resultsPath.resolve(fileName);
        if (Files.notExists(filePath)) {
            try {
                Files.createFile(filePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            objectMapper.writeValue(filePath.toFile(), object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Animal> getAnimals() {
        return new CopyOnWriteArrayList<>(animals);
    }
}
