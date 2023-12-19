package agents;

import java.math.BigDecimal;

public class Cat extends Pet {
    public Cat(String breed, String name, BigDecimal cost, String character) {
        super(breed, name, cost, character);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                '}';
    }
}
