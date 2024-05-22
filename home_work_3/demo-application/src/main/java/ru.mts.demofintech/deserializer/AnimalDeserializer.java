package ru.mts.demofintech.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import ru.mts.demofintech.entity.Animal;
import ru.mts.demofintech.entity.AnimalType;
import ru.mts.demofintech.entity.Breed;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Base64;

public class AnimalDeserializer extends JsonDeserializer<Animal> {

    @Override
    public Animal deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);
        AnimalType type = AnimalType.builder()
                .type(node.get("type").asText())
                .build();
        Breed breed = Breed.builder()
                .breed(node.get("breed").asText())
                .build();
        String name = node.get("name").asText();
        BigDecimal cost = BigDecimal.valueOf(node.get("cost").asDouble());
        String character = node.get("character").asText();
        LocalDate birthday = LocalDate.parse(node.get("birthdate").asText());
        String secretInformation = new String(Base64.getDecoder().decode(node.get("secretInformation").asText()));
        return Animal.builder()
                .animalType(type)
                .breed(breed)
                .name(name)
                .character(character)
                .cost(cost)
                .birthday(birthday)
                .build();
    }
}
