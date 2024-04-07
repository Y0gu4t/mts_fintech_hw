package ru.mts.demofintech.agents;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Bear extends Predator {
    public Bear(String breed, String name, BigDecimal cost, String character, LocalDate birthDate, String secretInformation) {
        super(breed, name, cost, character, birthDate, secretInformation);
    }

    @Override
    public String getType() {
        return "Bear";
    }

    @Override
    public String toString() {
        return "Bear{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", secretInformation='" + secretInformation + '\'' +
                '}';
    }
}
