package ru.mts.demofintech;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "application.habitat")
@Getter
@Setter
public class HabitatProperties {
    private List<String> areaList;
}
