package ru.mts.demofintech.web.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.mts.demofintech.web.dto.AnimalsProvidersDto;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AnimalsProvidersMapper implements RowMapper<AnimalsProvidersDto> {
    @Override
    public AnimalsProvidersDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new AnimalsProvidersDto(
                rs.getInt("id_animal_type"),
                rs.getInt("id_provider")
        );
    }
}
