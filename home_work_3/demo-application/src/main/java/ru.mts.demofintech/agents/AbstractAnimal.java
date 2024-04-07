package ru.mts.demofintech.agents;

import com.fasterxml.jackson.annotation.JsonValue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Objects;

public abstract class AbstractAnimal implements Animal {
    protected String breed;
    protected String name;
    protected BigDecimal cost;
    protected String character;
    protected LocalDate birthDate;
    protected String secretInformation;

    public AbstractAnimal(String breed, String name, BigDecimal cost, String character, LocalDate birthDate, String secretInformation) {
        this.breed = breed;
        this.name = name;
        this.cost = cost;
        this.character = character;
        this.birthDate = birthDate;
        this.secretInformation = secretInformation;
    }

    @Override
    public String getBreed() {
        return breed;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public BigDecimal getCost() {
        return cost;
    }

    @Override
    public String getCharacter() {
        return character;
    }

    @Override
    public String getType() {
        return "Animal";
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public String getSecretInformation() {
        return secretInformation;
    }

    public void setSecretInformation(String secretInformation) {
        this.secretInformation = secretInformation;
    }

    @Override
    public String encryptSecretInformation() {
        return Base64.getEncoder().encodeToString(secretInformation.getBytes());
    }

    @Override
    public String decryptSecretInformation(String secretInformation) {
        return new String(Base64.getDecoder().decode(secretInformation));
    }

    /**
     * Compares 2 animals by class, breed, name, character.
     * Returns {@code true} if all arguments match, {@code false} otherwise.
     *
     * @param o the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     * argument; {@code false} otherwise.
     * @author y0gu4t
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractAnimal that = (AbstractAnimal) o;
        return Objects.equals(breed, that.breed) &&
                Objects.equals(name, that.name) &&
                Objects.equals(character, that.character);
    }

    @Override
    @JsonValue
    public String toJsonString() {
        return "{type:" + getType() +
                ", breed:" + breed +
                ", name:" + name +
                ", cost:" + cost +
                ", character:" + character +
                ", birthdate:" + birthDate +
                ", secretInformation:" + encryptSecretInformation() + '}';
    }
}
