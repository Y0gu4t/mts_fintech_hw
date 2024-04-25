package ru.mts.demofintech.web.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.mts.demofintech.web.dto.CreatureDto;
import ru.mts.demofintech.web.mapper.CreatureMapper;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CreatureRepository {
    private final JdbcTemplate jdbcTemplate;
    private final CreatureMapper creatureMapper;

    public List<CreatureDto> getCreatures() {
        return jdbcTemplate.query(
                "select id_creature, name, age, type_id from animals.creature",
                creatureMapper
        );
    }
}
