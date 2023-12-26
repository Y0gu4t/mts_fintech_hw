package tools;

import agents.Animal;

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
     * An array of animals is presented at the entrance. The method displays duplicate animals to the console.
     * @param animals
     * */
    void findDuplicate(Animal[] animals);
}
