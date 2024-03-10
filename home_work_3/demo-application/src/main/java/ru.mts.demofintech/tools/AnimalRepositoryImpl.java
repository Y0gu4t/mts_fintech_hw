package ru.mts.demofintech.tools;

import ru.mts.demofintech.agents.Animal;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

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
        Map<String, LocalDate> leapYearMap = new HashMap<>();
        for (Animal animal : animals) {
            int year = animal.getBirthDate().getYear();
            if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
                leapYearMap.put(String.format("%s %s", animal.getType(), animal.getName()),animal.getBirthDate());
            }
        }
        return leapYearMap;
    }

    @Override
    public Map<Animal, Integer> findOlderAnimal(int years) {
        Map<Animal, Integer> olderAnimalMap = new HashMap<>();
        Animal oldestAnimal = animals.get(0);
        for (Animal animal : animals) {
            if (LocalDate.now().minusYears(years).isAfter(animal.getBirthDate())) {
                Integer animalYears = Period.between(animal.getBirthDate(), LocalDate.now()).getYears();
                olderAnimalMap.put(animal, animalYears);
            } else if (animal.getBirthDate().isBefore(oldestAnimal.getBirthDate())) {
                oldestAnimal = animal;
            }
        }
        if (olderAnimalMap.isEmpty()) {
            Integer animalYears = Period.between(oldestAnimal.getBirthDate(), LocalDate.now()).getYears();
            return Map.of(oldestAnimal, animalYears);
        }
        return olderAnimalMap;
    }

    @Override
    public Map<String, Integer> findDuplicate() {
        Map<String, Integer> duplicateAnimalMap = new HashMap<>();
        Set<Animal> animalSet = new HashSet<>();
        for (Animal animal: animals) {
            if (!animalSet.add(animal)) {
                String animalType = animal.getType();
                if (!duplicateAnimalMap.containsKey(animalType)) {
                    duplicateAnimalMap.put(animalType, 1);
                } else {
                    int newValue = duplicateAnimalMap.get(animalType) + 1;
                    duplicateAnimalMap.put(animalType, newValue);
                }
            }
        }
        return duplicateAnimalMap;
    }

    @Override
    public void printDuplicate() {
        System.out.println("\nAnimal Duplicates:\n");
        findDuplicate().forEach((k,v) -> System.out.println(k + " = " + v));
    }

    public List<Animal> getAnimals() {
        return new ArrayList<>(animals);
    }
}
