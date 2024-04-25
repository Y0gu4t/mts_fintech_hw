package ru.mts.demofintech.tools;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.demofintech.web.repository.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class SqlScheduler {
    private final TypeRepository typeRepository;
    private final CreatureRepository creatureRepository;
    private final HabitatRepository habitatRepository;
    private final ProviderRepository providerRepository;
    private final AnimalsHabitatsRepository animalsHabitatsRepository;
    private final AnimalsProvidersRepository animalsProvidersRepository;

    @Scheduled(cron = "*/10 * * * * ?")
    public void printAnimalsFromDatabase(){
        log.info(typeRepository.getTypes().toString());
        log.info(creatureRepository.getCreatures().toString());
        log.info(habitatRepository.getHabitats().toString());
        log.info(providerRepository.getProviders().toString());
        log.info(animalsHabitatsRepository.getAnimalsHabitats().toString());
        log.info(animalsHabitatsRepository.getAnimalsHabitats().toString());
    }
}
