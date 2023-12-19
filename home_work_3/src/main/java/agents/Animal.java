package agents;

import java.math.BigDecimal;

public interface Animal {

    /**
     * Returns the breed of the animal.
     *
     * @return the breed of the animal
     * @author y0gu4t
     */
    public String getBreed();

    /**
     * Returns the name of the animal.
     *
     * @return the name of the animal
     * @author y0gu4t
     */
    public String getName();

    /**
     * Returns the cost of the animal.
     *
     * @return the cost of the animal
     * @author y0gu4t
     */
    public BigDecimal getCost();

    /**
     * Returns the character of the animal.
     *
     * @return the cost of the animal
     * @author y0gu4t
     */
    public String getCharacter();
}
