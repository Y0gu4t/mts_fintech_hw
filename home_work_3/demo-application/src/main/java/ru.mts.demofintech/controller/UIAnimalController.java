package ru.mts.demofintech.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mts.demofintech.annotations.Logging;
import ru.mts.demofintech.dto.AnimalDTO;
import ru.mts.demofintech.entity.Animal;
import ru.mts.demofintech.repository.AnimalRepository;
import ru.mts.demofintech.repository.AnimalTypeRepository;
import ru.mts.demofintech.repository.BreedRepository;
import ru.mts.demofintech.service.AnimalService;

@Controller
@RequestMapping("/animals")
@RequiredArgsConstructor
public class UIAnimalController {
    private final AnimalRepository animalRepository;
    private final AnimalTypeRepository animalTypeRepository;
    private final BreedRepository breedRepository;
    private final AnimalService animalService;

    @GetMapping
    @Logging(entering = true, exiting = true)
    public String animalList(Model model) {
        model.addAttribute("animalList", animalRepository.findAll());
        return "animals";
    }

    @PostMapping("/delete/{id}")
    @Logging(value = "DELETE animal by id", entering = true, level = "WARN", args = true)
    public String deleteAnimalById(@PathVariable("id") Long id, Model model) {
        animalRepository.deleteById(id);
        model.addAttribute("animalList", animalRepository.findAll());
        return "redirect:/animals";
    }

    @GetMapping("/add")
    @Logging(value = "Go to page \"Add animal\"", entering = true)
    public String addAnimal(Model model) {
        model.addAttribute("animalDTO", new AnimalDTO());
        model.addAttribute("animalTypes", animalTypeRepository.findAll());
        model.addAttribute("breeds", breedRepository.findAll());
        return "add";
    }

    @PostMapping(value = "/add", params = "action=Add")
    @Logging(exiting = true)
    public String submitAddAnimal(AnimalDTO animalDTO) {
        animalService.createAnimal(animalDTO);
        return "redirect:/animals";
    }

    @PostMapping(value = "/add", params = "action=Cancel")
    @Logging(exiting = true)
    public String cancelAddAnimal() {
        return "redirect:/animals";
    }
}
