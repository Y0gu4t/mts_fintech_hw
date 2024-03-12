package ru.mts.demofintech.tools;

import ru.mts.demofintech.agents.Animal;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class AnimalRepositoryImpl implements AnimalRepository {
    CreateAnimalService createAnimalService;
    private final List<Animal> animals;

    public AnimalRepositoryImpl(CreateAnimalService createAnimalService) {
        this.createAnimalService = createAnimalService;
        animals = new ArrayList<>();
    }

    @PostConstruct
    public void createAnimals() {
        System.out.println();
        for (int i = 0; i < 20; i++) {
            animals.add(createAnimalService.createUniqueAnimal());
        }
        System.out.println();
    }

    @Override
    public Map<String, LocalDate> findLeapYearNames() {
        return animals.stream()
                .filter(animal -> animal.getBirthDate().getYear() % 4 == 0)
                .filter(animal -> animal.getBirthDate().getYear() % 100 != 0 || animal.getBirthDate().getYear() % 400 == 0)
                .collect(Collectors.toMap(
                        animal -> String.format("%s %s", animal.getType(), animal.getName()),
                        Animal::getBirthDate,
                        (animal1, animal2) -> animal1));
    }

    @Override
    public Map<Animal, Integer> findOlderAnimal(int years) {
        Map<Animal, Integer> olderAnimalMap = animals.stream()
                .filter(animal -> LocalDate.now().minusYears(years).isAfter(animal.getBirthDate()))
                .collect(Collectors.toMap(
                        animal -> animal,
                        animal -> Period.between(animal.getBirthDate(), LocalDate.now()).getYears()));

        if (olderAnimalMap.isEmpty()) {
            return animals.stream()
                    .min(Comparator.comparing(Animal::getBirthDate))
                    .map(animal -> Map.of(animal, Period.between(animal.getBirthDate(), LocalDate.now()).getYears()))
                    .orElse(Collections.emptyMap());
        }

        return olderAnimalMap;
    }

    @Override
    public Map<String, List<Animal>> findDuplicate() {
        return animals.stream()
                .collect(Collectors.groupingBy(Animal::getType,
                        Collectors.collectingAndThen(Collectors.toList(),
                                list -> list.stream()
                                        .filter(animal -> list.stream()
                                                .filter(a -> a.equals(animal)).count() > 1)
                                        .collect(Collectors.toList()))));
    }

    @Override
    public void printDuplicate() {
        System.out.println("\nAnimal Duplicates:\n");
        findDuplicate().forEach((k, v) -> System.out.println(k + " = " + v));
    }

    @Override
    public void findAverageAge() {
        System.out.printf("\nAverage animal age: %.2f\n",
                animals.stream()
                        .mapToInt(animal -> Period.between(animal.getBirthDate(), LocalDate.now()).getYears())
                        .average()
                        .orElseThrow(() -> new RuntimeException("Ошибка при подсчёте среднего возраста")));
    }

    @Override
    public List<Animal> findOldAndExpensive() {
        BigDecimal averageAnimalCost = animals.stream()
                .map(Animal::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(animals.size()));

        return animals.stream()
                .filter(animal -> LocalDate.now().minusYears(5).isAfter(animal.getBirthDate()))
                .filter(animal -> animal.getCost().compareTo(averageAnimalCost) == 1)
                .sorted(Comparator.comparing(Animal::getBirthDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findMinConstAnimals() {
        return animals.stream()
                .sorted(Comparator.comparing(Animal::getCost))
                .limit(3)
                .map(Animal::getName)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    public List<Animal> getAnimals() {
        return new ArrayList<>(animals);
    }
}
