package ru.mts.demofintech.service;

import ru.mts.demofintech.dao.AnimalTypeDao;
import ru.mts.demofintech.dao.BreedDao;
import ru.mts.demofintech.entity.Animal;
import ru.mts.demofintech.entity.AnimalType;
import ru.mts.demofintech.entity.Breed;

import java.io.FileNotFoundException;
import java.util.*;

public interface CreateAnimalService {
    /**
     * Creates unique animal and prints created animal's information (breed, name, cost and character).
     * The animal type is determined randomly from the list of animal types.
     * String attributes are defined as "attribute name" plus a random index.
     * The cost of the animal is determined using a random double value, which is scaled to 2 decimal places.
     * @param animalTypeDao uses to get animalType from database
     * @author y0gu4t
     */
    Animal createUniqueAnimal(AnimalTypeDao animalTypeDao, BreedDao breedDao) throws FileNotFoundException;

    /**
     * Creates animal types for animal's
     * @author y0gu4t
     * */
    List<AnimalType> createAnimalTypes();

    List<Breed> createAnimalBreed();

    /**
     * Writes information about the animal (breed, name, price, date of birth) to
     * the logData.txt file with a new line.
     * @param animal animal to write in file
     * @author y0gu4t
     * */
    void writeAnimalInfo(Animal animal);
}
