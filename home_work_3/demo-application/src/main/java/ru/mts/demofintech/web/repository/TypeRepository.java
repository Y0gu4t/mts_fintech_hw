package ru.mts.demofintech.web.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.mts.demofintech.web.dto.TypeDto;
import ru.mts.demofintech.web.mapper.TypeMapper;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TypeRepository {
    private final JdbcTemplate jdbcTemplate;
    private final TypeMapper typeMapper;

    public List<TypeDto> getTypes() {
        return jdbcTemplate.query(
                "select id_type, type, is_wild from animals.animal_type",
                typeMapper
        );
    }
}
