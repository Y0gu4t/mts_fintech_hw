package ru.mts.demofintech.web.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.mts.demofintech.web.dto.HabitatDto;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class HabitatMapper implements RowMapper<HabitatDto> {
    @Override
    public HabitatDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return HabitatDto.builder()
                .id(rs.getInt("id_area"))
                .area(rs.getString("area"))
                .build();
    }
}
