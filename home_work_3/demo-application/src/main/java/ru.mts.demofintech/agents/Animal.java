package ru.mts.demofintech.agents;

import java.math.BigDecimal;
import java.time.LocalDate;

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
     * @return the character of the animal
     * @author y0gu4t
     */
    public String getCharacter();

    /**
     * Returns the birthday of the animal.
     *
     * @return the birthday of the animal
     * @author y0gu4t
     */
    public LocalDate getBirthDate();

    /**
     * Returns the type of the animal.
     *
     * @return the type of the animal
     * @author y0gu4t
     */
    public String getType();

    /**
     * Returns the secret information about animal
     * @return the secret information
     * @author y0gu4t
     * */
    public String getSecretInformation();

    /**
     * Sets the secret information about animal
     * @param secretInformation the secret information about animal
     * @author y0gu4t
     * */
    public void setSecretInformation(String secretInformation);

    /**
     * encrypt the secret information about animal
     * @author y0gu4t
     * */
    public String encryptSecretInformation();

    /**
     * decrypt the secret information about animal
     * @param secretInformation the secret information about animal
     * @author y0gu4t
     * */
    public String decryptSecretInformation(String secretInformation);

    /**
     * Converts the class to json
     * */
    public String toJsonString();
}
