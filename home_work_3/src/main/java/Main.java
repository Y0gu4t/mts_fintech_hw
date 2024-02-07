import agents.Animal;
import tools.CreateAnimalService;
import tools.CreateAnimalServiceImpl;
import tools.SearchService;
import tools.SearchServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CreateAnimalService createAnimalService = new CreateAnimalServiceImpl();
        CreateAnimalServiceImpl createAnimalServiceImpl = new CreateAnimalServiceImpl();
        SearchService searchService = new SearchServiceImpl();
        Animal[] animals = createAnimalService.createUniqueAnimals(20);
        String[] leapYearNames = searchService.findLeapYearNames(animals);
        Animal[] olderAnimals = searchService.findOlderAnimal(animals, 5);
        searchService.printDuplicate(animals);
        System.out.println("\nЖивотные, которые родились в високостный год:\n");
        for (String name:
             leapYearNames) {
            System.out.println(name);
        }
        System.out.println("\nЖивотные, которые старше 5 лет\n");
        for (Animal animal:
             olderAnimals) {
            System.out.println(animal);
        }
    }
}
