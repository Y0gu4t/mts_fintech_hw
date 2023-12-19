import tools.CreateAnimalService;
import tools.CreateAnimalServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CreateAnimalService createAnimalService = new CreateAnimalServiceImpl();
        CreateAnimalServiceImpl createAnimalServiceImpl = new CreateAnimalServiceImpl();
        System.out.println("\n\nВызов default метода:\n");
        createAnimalServiceImpl.defaultCreateUniqueAnimals();
        System.out.println("\n\nВызов переопределенного метода:\n");
        createAnimalService.createUniqueAnimals();
        System.out.println("\n\nВызов перегруженного метода:\n");
        createAnimalService.createUniqueAnimals(5);
    }
}
