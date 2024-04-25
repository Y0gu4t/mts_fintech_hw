package ru.mts.demofintech.web.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.mts.demofintech.web.dto.AnimalsHabitatsDto;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AnimalsHabitatsMapper implements RowMapper<AnimalsHabitatsDto> {
    @Override
    public AnimalsHabitatsDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new AnimalsHabitatsDto(
                rs.getInt("id_animal_type"),
                rs.getInt("id_area")
        );
    }
}
