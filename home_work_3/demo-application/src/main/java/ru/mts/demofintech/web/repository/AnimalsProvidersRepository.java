package ru.mts.demofintech.web.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.mts.demofintech.web.dto.AnimalsProvidersDto;
import ru.mts.demofintech.web.mapper.AnimalsProvidersMapper;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AnimalsProvidersRepository {
    private final JdbcTemplate jdbcTemplate;
    private final AnimalsProvidersMapper animalsProvidersMapper;

    public List<AnimalsProvidersDto> getAnimalsProviders() {
        return jdbcTemplate.query(
                "select id_animal_type, id_provider from animals.animals_providers",
                animalsProvidersMapper
        );
    }
}
