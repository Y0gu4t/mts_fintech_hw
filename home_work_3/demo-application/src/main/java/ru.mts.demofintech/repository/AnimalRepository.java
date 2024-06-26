package ru.mts.demofintech.repository;

import ru.mts.demofintech.entity.Animal;

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
    Map<String, List<Animal>> findDuplicate();

    /**
     * An array of animals is presented at the entrance. The method print duplicate animals to console.
     */
    void printDuplicate();

    /**
     * An array of animals is presented at the entrance. The method print average animals age to console.
     * */
    void findAverageAge();

    /**
     * An array of animals is presented at the entrance. The method returns a list of animals that are older than 5 years and
     * more expensive than the average cost of animals. The list is sorted by date of birth ascending
     * */
    List<Animal> findOldAndExpensive();

    /**
     * An array of animals is presented at the entrance. The method returns a list of 3 animal names that cost the least.
     * List sorted in reverse alphabetical order
     * */
    List<String> findMinConstAnimals();

    /**
     * The method returns an array of animals that was created via animalRepository
     *
     * @return animals;
     * */
    List<Animal> getAnimals();

    /**
     * Creates a file called fileName, if such a file has not yet been created,
     * and writes the result of the method execution into it in Json format
     * @param fileName file name to write the result
     * @param object method's result
     * */
    void writeMethodResultToJson(String fileName, Object object);
}
