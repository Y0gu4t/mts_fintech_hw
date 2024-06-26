package ru.mts.demofintech;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "application.animal")
@Getter
@Setter
public class AnimalProperties {
    private List<String> nameList;
    private List<String> typeList;
    private List<String> isWildList;
    private List<String> breedList;
}
