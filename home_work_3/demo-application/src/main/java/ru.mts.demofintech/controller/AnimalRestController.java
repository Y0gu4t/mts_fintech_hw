package ru.mts.demofintech.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mts.demofintech.annotations.Logging;
import ru.mts.demofintech.dto.AnimalDTO;
import ru.mts.demofintech.entity.Animal;
import ru.mts.demofintech.repository.AnimalRepository;
import ru.mts.demofintech.service.AnimalService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/animals/rest")
public class AnimalRestController {
    private final AnimalRepository animalRepository;
    private final AnimalService animalService;

    @GetMapping
    @Logging(value = "GET animal list")
    public List<Animal> animalList() {
        return animalRepository.findAll();
    }

    @PostMapping("/add")
    @Logging(entering = true, exiting = true, args = true, result = true)
    public String addAnimal(@RequestBody AnimalDTO animalDTO) {
        animalService.createAnimal(animalDTO);
        return "OK";
    }

    @DeleteMapping("/delete/{id}")
    @Logging(entering = true, level = "WARN", args = true)
    public String deleteAnimalById(@PathVariable("id") Long id) {
        animalRepository.deleteById(id);
        return "OK";
    }
}
