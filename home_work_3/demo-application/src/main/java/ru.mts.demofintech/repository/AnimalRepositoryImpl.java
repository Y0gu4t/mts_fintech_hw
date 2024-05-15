package ru.mts.demofintech.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.mts.demofintech.dao.AnimalDao;
import ru.mts.demofintech.dao.AnimalTypeDao;
import ru.mts.demofintech.dao.BreedDao;
import ru.mts.demofintech.entity.Animal;
import ru.mts.demofintech.exceptions.AnimalListOutOfBoundException;
import ru.mts.demofintech.exceptions.WrongYearException;
import ru.mts.demofintech.service.CreateAnimalService;

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

@Repository
@RequiredArgsConstructor
public class AnimalRepositoryImpl implements AnimalRepository {
    private final CreateAnimalService createAnimalService;
    private final ObjectMapper objectMapper;
    private final AnimalDao animalDao;
    private final AnimalTypeDao animalTypeDao;
    private final BreedDao breedDao;

    @Value("${application.results}")
    private Path resultsPath;

    @PostConstruct
    public void init() throws FileNotFoundException {
        createAnimalService.createAnimalTypes().forEach(animalTypeDao::create);
        createAnimalService.createAnimalBreed().forEach(breedDao::create);
        for (int i = 0; i < 40; i++) {
            animalDao.create(createAnimalService.createUniqueAnimal(animalTypeDao, breedDao));
        }
    }

    @Override
    public Map<String, LocalDate> findLeapYearNames() {
        Map<String, LocalDate> result = animalDao.findAll().stream()
                .filter(animal -> animal.getBirthday().getYear() % 4 == 0)
                .filter(animal -> animal.getBirthday().getYear() % 100 != 0 || animal.getBirthday().getYear() % 400 == 0)
                .collect(Collectors.toConcurrentMap(
                        animal -> String.format("%s %s", animal.getAnimalType(), animal.getName()),
                        Animal::getBirthday,
                        (animal1, animal2) -> animal1));
        writeMethodResultToJson("findLeapYearNames.json", result);
        return result;
    }

    @Override
    public Map<Animal, Integer> findOlderAnimal(int years) throws WrongYearException {
        if (years < 0) {
            throw new WrongYearException("Negative age indicated");
        }
        Map<Animal, Integer> olderAnimalMap = animalDao.findAll().stream()
                .filter(animal -> LocalDate.now().minusYears(years).isAfter(animal.getBirthday()))
                .collect(Collectors.toConcurrentMap(
                        animal -> animal,
                        animal -> Period.between(animal.getBirthday(), LocalDate.now()).getYears()));

        if (olderAnimalMap.isEmpty()) {
            olderAnimalMap = animalDao.findAll().stream()
                    .min(Comparator.comparing(Animal::getBirthday))
                    .map(animal -> Map.of(animal, Period.between(animal.getBirthday(), LocalDate.now()).getYears()))
                    .orElse(Collections.emptyMap());
        }
        writeMethodResultToJson("findOlderAnimal.json", olderAnimalMap);
        return olderAnimalMap;
    }

    @Override
    public Map<String, List<Animal>> findDuplicate() {
        Map<String, List<Animal>> result = null; /*animals.stream()
                .collect(Collectors.groupingBy(Animal::getAnimalType,
                        Collectors.collectingAndThen(Collectors.toList(),
                                list -> list.stream()
                                        .filter( animal -> list.stream()
                                                .filter(a -> a.equals(animal)).count() > 1)
                                        .collect(Collectors.collectingAndThen(Collectors.toList(), CopyOnWriteArrayList::new)))));
        writeMethodResultToJson("findDuplicate.json", result);*/
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
        Double result = animalDao.findAll().stream()
                .mapToInt(animal -> Period.between(animal.getBirthday(), LocalDate.now()).getYears())
                .average()
                .orElseThrow(() -> new RuntimeException("Ошибка при подсчёте среднего возраста"));
        writeMethodResultToJson("findAverageAge.json", result);
    }

    @Override
    public List<Animal> findOldAndExpensive() {
        List<Animal> animals = animalDao.findAll();
        BigDecimal averageAnimalCost = animals.stream()
                .map(Animal::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(animals.size()));

        List<Animal> result = animals.stream()
                .filter(animal -> LocalDate.now().minusYears(5).isAfter(animal.getBirthday()))
                .filter(animal -> animal.getCost().compareTo(averageAnimalCost) == 1)
                .sorted(Comparator.comparing(Animal::getBirthday))
                .collect(Collectors.collectingAndThen(Collectors.toList(), CopyOnWriteArrayList::new));

        writeMethodResultToJson("findOldAndExpensive.json", result);

        return result;
    }

    @Override
    public List<String> findMinConstAnimals() throws AnimalListOutOfBoundException {
        List<Animal> animals = animalDao.findAll();
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
        return new CopyOnWriteArrayList<>(animalDao.findAll());
    }
}
