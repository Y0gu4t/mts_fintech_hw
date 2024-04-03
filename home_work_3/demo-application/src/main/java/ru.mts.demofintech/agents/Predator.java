package ru.mts.demofintech.agents;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Predator extends AbstractAnimal {
    public Predator(String breed, String name, BigDecimal cost, String character, LocalDate birthDate, String secretInformation) {
        super(breed, name, cost, character, birthDate, secretInformation);
    }
}
