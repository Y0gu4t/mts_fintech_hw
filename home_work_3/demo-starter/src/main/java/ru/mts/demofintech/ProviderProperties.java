package ru.mts.demofintech;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "application.provider")
@Getter
@Setter
public class ProviderProperties {
    private List<String> nameList;
    private List<String> phoneList;
}
