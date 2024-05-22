package ru.mts.demofintech.web.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.mts.demofintech.web.dto.TypeDto;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class TypeMapper implements RowMapper<TypeDto> {

    @Override
    public TypeDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return TypeDto.builder()
                .id(rs.getInt("id_type"))
                .type(rs.getString("type"))
                .isWild(rs.getBoolean("is_wild"))
                .build();
    }
}
