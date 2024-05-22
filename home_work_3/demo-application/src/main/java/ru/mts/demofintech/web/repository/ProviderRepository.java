package ru.mts.demofintech.web.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.mts.demofintech.web.dto.ProviderDto;
import ru.mts.demofintech.web.mapper.ProviderMapper;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProviderRepository {
    private final JdbcTemplate jdbcTemplate;
    private final ProviderMapper providerMapper;

    public List<ProviderDto> getProviders() {
        return jdbcTemplate.query(
                "select id_provider, name, phone from animals.provider",
                providerMapper
        );
    }
}
