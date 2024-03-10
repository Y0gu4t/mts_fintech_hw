package ru.mts.demofintech.tools;

import ru.mts.demofintech.agents.Animal;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AnimalRepository {
    /**
     * An array of animals is presented at the entrance. The method returns an array of names of animals that were born in a leap year.
     *
     * @return names of animals
     */
    Map<String, LocalDate> findLeapYearNames();

    /**
     * The input is an array of animals and the number of years. The method returns an array of animals that are older than the given year.
     *
     * @param years
     * @return animals
     */
    Map<Animal, Integer> findOlderAnimal(int years);

    /**
     * An array of animals is presented at the entrance. The method returns duplicate animals.
     *
     * @return animals
     */
    Map<String, Integer> findDuplicate();

    /**
     * An array of animals is presented at the entrance. The method print duplicate animals to console.
     */
    void printDuplicate();

    /**
     * The method returns an array of animals that was created via animalRepository
     *
     * @return animals;
     * */
    List<Animal> getAnimals();
}
