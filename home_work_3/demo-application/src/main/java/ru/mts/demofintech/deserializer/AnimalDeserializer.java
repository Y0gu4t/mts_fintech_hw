package ru.mts.demofintech.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import ru.mts.demofintech.agents.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Base64;

public class AnimalDeserializer extends JsonDeserializer<Animal> {

    @Override
    public Animal deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);
        String type = node.get("type").asText();
        String breed = node.get("breed").asText();
        String name = node.get("name").asText();
        BigDecimal cost = BigDecimal.valueOf(node.get("cost").asDouble());
        String character = node.get("character").asText();
        LocalDate birthday = LocalDate.parse(node.get("birthdate").asText());
        String secretInformation = new String(Base64.getDecoder().decode(node.get("secretInformation").asText()));
        switch (type) {
            case "Bear":
                return new Bear(breed, name, cost, character, birthday, secretInformation);
            case "Cat":
                return new Cat(breed, name, cost, character, birthday, secretInformation);
            case "Fish":
                return new Fish(breed, name, cost, character, birthday, secretInformation);
            case "Fox":
                return new Fox(breed, name, cost, character, birthday, secretInformation);
            default:
                return null;
        }
    }
}
