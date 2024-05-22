package ru.mts.demofintech.agents;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Fish extends Pet {
    public Fish(String breed, String name, BigDecimal cost, String character, LocalDate birthDate, String secretInformation) {
        super(breed, name, cost, character, birthDate, secretInformation);
    }

    @Override
    public String getType() {
        return "Fish";
    }

    @Override
    public String toString() {
        return "Fish{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", secretInformation='" + secretInformation + '\'' +
                '}';
    }
}
