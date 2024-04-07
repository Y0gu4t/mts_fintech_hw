package ru.mts.demofintech.agents;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Fox extends Predator {
    public Fox(String breed, String name, BigDecimal cost, String character, LocalDate birthDate, String secretInformation) {
        super(breed, name, cost, character, birthDate, secretInformation);
    }

    @Override
    public String getType() {
        return "Fox";
    }

    @Override
    public String toString() {
        return "Fox{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", secretInformation='" + secretInformation + '\'' +
                '}';
    }
}
