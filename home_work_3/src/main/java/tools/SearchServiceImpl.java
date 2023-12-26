package tools;

import agents.Animal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SearchServiceImpl implements SearchService {
    @Override
    public String[] findLeapYearNames(Animal[] animals) {
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
    public Animal[] findOlderAnimal(Animal[] animals, int years) {
        List<Animal> olderAnimals = new ArrayList<>();
        for (int i = 0; i < animals.length; i++) {
            if (LocalDate.now().getYear() - animals[i].getBirthDate().getYear() > years) {
                olderAnimals.add(animals[i]);
            }
        }
        return olderAnimals.toArray(new Animal[0]);
    }

    @Override
    public void findDuplicate(Animal[] animals) {
        System.out.println("\nДубликаты животных:\n");
        for (int i = 0; i < animals.length; i++) {
            for (int j = i+1; j < animals.length; j++){
                if(animals[i].equals(animals[j])){
                    System.out.println(animals[i]);
                }
            }
        }
    }
}
