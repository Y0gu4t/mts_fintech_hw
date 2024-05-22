package ru.mts.demofintech.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import ru.mts.demofintech.Config;
import ru.mts.demofintech.dao.AnimalTypeDao;
import ru.mts.demofintech.dao.BreedDao;
import ru.mts.demofintech.entity.Animal;
import ru.mts.demofintech.entity.AnimalType;
import ru.mts.demofintech.entity.Breed;
import ru.mts.demofintech.tools.AnimalFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Slf4j
public class CreateAnimalServiceImpl implements CreateAnimalService {
    private final Config config;
    @Value("${application.path}")
    private Path logDataPath;
    private static boolean logDataOnModify = false;

    public CreateAnimalServiceImpl(Config config) {
        this.config = config;
    }

    @Override
    public List<AnimalType> createAnimalTypes() {
        List<AnimalType> animalTypeList = new ArrayList<>();
        Random random = new Random();
        config.get("animal")
                .get("type")
                .forEach(e -> animalTypeList.add(AnimalType.builder()
                        .type(e)
                        .isWild(random.nextBoolean())
                        .build()));
        return animalTypeList;
    }

    @Override
    public List<Breed> createAnimalBreed() {
        List<Breed> breedList = new ArrayList<>();
        config.get("animal")
                .get("breed")
                .forEach(e -> breedList.add(Breed.builder()
                                .breed(e)
                        .build()));
        return breedList;
    }

    @Override
    public Animal createUniqueAnimal(AnimalTypeDao animalTypeDao, BreedDao breedDao) {
        AnimalFactory animalFactory = new AnimalFactory(config, animalTypeDao, breedDao);
        Animal animal = animalFactory.createAnimal();
        log.info(animal.toString());
        //writeAnimalInfo(animal);
        return animal;
    }

    @Override
    public void writeAnimalInfo(Animal animal) {
        try {
            if (!logDataOnModify) {
                Files.writeString(logDataPath, "");
                logDataOnModify = true;
            }
            List<String> lines = Files.readAllLines(logDataPath);
            lines.add(String.format("%d %s %s %.2f %tD",
                    lines.size() + 1,
                    animal.getBreed(),
                    animal.getName(),
                    animal.getCost().doubleValue(),
                    animal.getBirthday()));
            Files.write(logDataPath, lines);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
