package ru.mts.demofintech.web.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.mts.demofintech.web.dto.AnimalsHabitatsDto;
import ru.mts.demofintech.web.mapper.AnimalsHabitatsMapper;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AnimalsHabitatsRepository {
    private final JdbcTemplate jdbcTemplate;
    private final AnimalsHabitatsMapper animalsHabitatsMapper;

    public List<AnimalsHabitatsDto> getAnimalsHabitats() {
        return jdbcTemplate.query(
                "select id_animal_type, id_area from animals.animals_habitats",
                animalsHabitatsMapper
        );
    }
}
