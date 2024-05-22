package ru.mts.demofintech.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.demofintech.dao.AnimalDao;
import ru.mts.demofintech.dao.AnimalTypeDao;
import ru.mts.demofintech.dao.BreedDao;
import ru.mts.demofintech.service.CreateAnimalService;

import java.io.FileNotFoundException;

@Component
@RequiredArgsConstructor
@Slf4j
public class SqlScheduler {
    private final AnimalDao animalDao;
    private final AnimalTypeDao animalTypeDao;
    private final BreedDao breedDao;
    private final CreateAnimalService createAnimalService;

    @Scheduled(cron = "*/10 * * * * ?")
    public void printAnimalsFromDatabase() throws FileNotFoundException {
        animalDao.create(createAnimalService.createUniqueAnimal(animalTypeDao, breedDao));
        log.info(animalDao.findAll().toString());
    }
}
