package ru.mts.demofintech.tools;

import ru.mts.demofintech.agents.Animal;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AnimalRepositoryImpl implements AnimalRepository {
    CreateAnimalService createAnimalService;
    private Animal[] animals;

    public AnimalRepositoryImpl(CreateAnimalService createAnimalService) {
        this.createAnimalService = createAnimalService;
    }

    @PostConstruct
    public void createAnimals() {
        animals = new Animal[20];
        System.out.println();
        for (int i = 0; i < animals.length; i++) {
            animals[i] = createAnimalService.createUniqueAnimal();
        }
        System.out.println();
    }

    @Override
    public String[] findLeapYearNames() {
        List<String> leapYearNames = new ArrayList<>();
        for (Animal animal : animals) {
            int year = animal.getBirthDate().getYear();
            if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
                leapYearNames.add(animal.getName());
            }
        }
        return leapYearNames.toArray(new String[0]);
    }

    @Override
    public Animal[] findOlderAnimal(int years) {
        List<Animal> olderAnimals = new ArrayList<>();
        for (int i = 0; i < animals.length; i++) {
            if (LocalDate.now().minusYears(years).isAfter(animals[i].getBirthDate())) {
                olderAnimals.add(animals[i]);
            }
        }
        return olderAnimals.toArray(new Animal[0]);
    }

    @Override
    public List<Animal> findDuplicate() {
        List<Animal> duplicateAnimal = new ArrayList<>();
        for (int i = 0; i < animals.length; i++) {
            for (int j = i + 1; j < animals.length; j++) {
                if (animals[i].equals(animals[j])) {
                    duplicateAnimal.add(animals[i]);
                }
            }
        }
        return duplicateAnimal;
    }

    @Override
    public void printDuplicate() {
        System.out.println("\nAnimal Duplicates:\n");
        for (Animal animal : findDuplicate()) {
            System.out.println(animal);
        }
    }

    public Animal[] getAnimals() {
        return animals;
    }
}
