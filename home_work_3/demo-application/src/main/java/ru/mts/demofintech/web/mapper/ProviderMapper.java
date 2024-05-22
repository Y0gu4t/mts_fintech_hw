package ru.mts.demofintech.web.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.mts.demofintech.web.dto.ProviderDto;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProviderMapper implements RowMapper<ProviderDto> {
    @Override
    public ProviderDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ProviderDto.builder()
                .id(rs.getInt("id_provider"))
                .name(rs.getString("name"))
                .phone(rs.getString("phone"))
                .build();
    }
}
