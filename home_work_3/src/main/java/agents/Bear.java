package agents;

import java.math.BigDecimal;

public class Bear extends Predator {
    public Bear(String breed, String name, BigDecimal cost, String character) {
        super(breed, name, cost, character);
    }

    @Override
    public String toString() {
        return "Bear{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                '}';
    }
}
