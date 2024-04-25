package ru.mts.demofintech.web.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.mts.demofintech.web.dto.CreatureDto;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CreatureMapper implements RowMapper<CreatureDto> {
    @Override
    public CreatureDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return CreatureDto.builder()
                .id(rs.getLong("id_creature"))
                .name(rs.getString("name"))
                .typeId(rs.getInt("type_id"))
                .age(rs.getShort("age"))
                .build();
    }
}
