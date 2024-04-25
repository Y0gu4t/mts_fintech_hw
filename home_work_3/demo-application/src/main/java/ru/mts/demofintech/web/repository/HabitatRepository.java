package ru.mts.demofintech.web.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.mts.demofintech.web.dto.HabitatDto;
import ru.mts.demofintech.web.mapper.HabitatMapper;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class HabitatRepository {
    private final JdbcTemplate jdbcTemplate;
    private final HabitatMapper habitatMapper;

    public List<HabitatDto> getHabitats() {
        return jdbcTemplate.query(
                "select id_area, area from animals.habitat",
                habitatMapper
        );
    }
}
