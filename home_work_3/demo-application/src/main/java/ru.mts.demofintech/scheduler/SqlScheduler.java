package ru.mts.demofintech.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.demofintech.repository.AnimalRepository;

import java.io.FileNotFoundException;

@Component
@RequiredArgsConstructor
@Slf4j
public class SqlScheduler {
    private final AnimalRepository animalRepository;

    @Scheduled(cron = "0 */2 * * * ?")
    public void printAnimalsFromDatabase() {
        log.info(animalRepository.findAll().toString());
    }
}
