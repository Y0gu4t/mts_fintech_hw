package tools;

import agents.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AnimalRepositoryImpl implements AnimalRepository {
    @Autowired
    AnimalConfiguration animalConfiguration;
    private Animal[] animals;

    @PostConstruct
    public void createAnimals() {
        animals = new Animal[10];
        for (int i = 0; i < animals.length; i++) {
            animals[i] = animalConfiguration.createAnimalService().createUniqueAnimal();
        }
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
        System.out.println("\nДубликаты животных:\n");
        for (Animal animal : findDuplicate()) {
            System.out.println(animal);
        }
    }
}
