package tools;

import agents.Animal;

import java.util.List;

public interface SearchService {
    /**
     * An array of animals is presented at the entrance. The method returns an array of names of animals that were born in a leap year.
     * @param animals
     * @return names of animals
     * */
    String[] findLeapYearNames(Animal[] animals);

    /**
     * The input is an array of animals and the number of years. The method returns an array of animals that are older than the given year.
     * @param animals
     * @param years
     * @return animals
     * */
    Animal[] findOlderAnimal(Animal[] animals, int years);

    /**
     * An array of animals is presented at the entrance. The method returns duplicate animals.
     * @param animals
     * @return animals
     * */
    List<Animal> findDuplicate(Animal[] animals);

    /**
     * An array of animals is presented at the entrance. The method print duplicate animals to console.
     * @param animals
     * */
    void printDuplicate(Animal[] animals);
}
