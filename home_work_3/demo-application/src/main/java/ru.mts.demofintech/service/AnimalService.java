package ru.mts.demofintech.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mts.demofintech.annotations.Logging;
import ru.mts.demofintech.dto.AnimalDTO;
import ru.mts.demofintech.entity.Animal;
import ru.mts.demofintech.entity.AnimalType;
import ru.mts.demofintech.entity.Breed;
import ru.mts.demofintech.repository.AnimalRepository;
import ru.mts.demofintech.repository.AnimalTypeRepository;
import ru.mts.demofintech.repository.BreedRepository;

@Service
@RequiredArgsConstructor
public class AnimalService {
    private final AnimalRepository animalRepository;
    private final AnimalTypeRepository animalTypeRepository;
    private final BreedRepository breedRepository;

    @Logging(value = "Create animal using AnimalDTO", entering = true, exiting = true)
    public Animal createAnimal(AnimalDTO animalDTO) {
        AnimalType animalType = animalTypeRepository.findById(animalDTO.getAnimalTypeId())
                .orElseThrow(() -> new RuntimeException("AnimalType not found"));
        Breed breed = breedRepository.findById(animalDTO.getBreedId())
                .orElseThrow(() -> new RuntimeException("Breed not found"));
        Animal animal = Animal.builder()
                .name(animalDTO.getName())
                .character(animalDTO.getCharacter())
                .cost(animalDTO.getCost())
                .birthday(animalDTO.getBirthday())
                .animalType(animalType)
                .breed(breed)
                .build();
        return animalRepository.save(animal);
    }
}
