package ru.mts.demofintech.tools;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mts.demofintech.Config;
import ru.mts.demofintech.dao.AnimalTypeDao;
import ru.mts.demofintech.dao.BreedDao;
import ru.mts.demofintech.entity.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class AnimalFactory {
    /**
     * Creates an animal with an input type and a random breed, name and personality. Returns the created animal.
     *
     * @param type class of the animal being created
     * @return Animal
     * @author y0gu4t
     */
    private final List<String> animalNameList;
    private final List<String> habitatAreaList;
    private final List<String> providerNameList;
    private final List<String> providerPhoneList;
    private final Path secretInformationPath;
    private final AnimalTypeDao animalTypeDao;
    private final BreedDao breedDao;

    @Autowired
    public AnimalFactory(Config config, AnimalTypeDao animalTypeDao, BreedDao breedDao) {
        animalNameList = config.get("animal").get("name");
        habitatAreaList = config.get("habitat").get("area");
        providerNameList = config.get("provider").get("name");
        providerPhoneList = config.get("provider").get("phone");
        this.animalTypeDao = animalTypeDao;
        this.breedDao = breedDao;
        secretInformationPath = Paths.get("demo-application/src/main/resources/secretStore/secretInformation.txt");
    }


    public Animal createAnimal() {
        Random random = new Random();
        AnimalType animalType = animalTypeDao.findAll().get(random.nextInt(3) + 1);
        Breed breed = breedDao.findAll().get(random.nextInt(3)+1);
        String name = animalNameList.get(random.nextInt(animalNameList.size()));
        String character = "Character#" + random.nextInt(2);
        LocalDate birthday = LocalDate.of(random.nextInt(24) + 2000,
                random.nextInt(12) + 1,
                random.nextInt(28) + 1);
        BigDecimal cost = BigDecimal.valueOf(random.nextDouble()).setScale(2, RoundingMode.HALF_UP);
        int providersCount = random.nextInt(3) + 1;
        Set<Provider> providers = new HashSet<>();
        for (int i = 0; i < providersCount; i++) {
            providers.add(createRandomProvider(random));
        }
        int habitatsCount = random.nextInt(3) + 1;
        Set<Habitat> habitats = new HashSet<>();
        for (int i = 0; i < habitatsCount; i++) {
            habitats.add(createRandonHabitat(random));
        }
        try {
            List<String> lines = Files.readAllLines(secretInformationPath);
            String secretInformation = lines.get(random.nextInt(lines.size()));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return Animal.builder()
                .animalType(animalType)
                .name(name)
                .character(character)
                .breed(breed)
                .cost(cost)
                .birthday(birthday)
                .providers(providers)
                .habitats(habitats)
                .build();
    }

    private Habitat createRandonHabitat(Random random) {
        return Habitat.builder()
                .area(habitatAreaList.get(random.nextInt(habitatAreaList.size())))
                .animals(new HashSet<>())
                .build();
    }

    private Provider createRandomProvider(Random random) {
        return Provider.builder()
                .name(providerNameList.get(random.nextInt(providerNameList.size())))
                .phone(providerPhoneList.get(random.nextInt(providerPhoneList.size())))
                .build();
    }
}
