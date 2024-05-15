package ru.mts.demofintech.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import ru.mts.demofintech.entity.Animal;

import java.io.IOException;

public class AnimalSerializer extends JsonSerializer<Animal> {
    @Override
    public void serialize(Animal animal, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("type", animal.getAnimalType().getType());
        gen.writeStringField("breed", animal.getBreed().getBreed());
        gen.writeStringField("name", animal.getName());
        gen.writeNumberField("cost", animal.getCost());
        gen.writeStringField("character", animal.getCharacter());
        gen.writeObjectField("birthdate", animal.getBirthday());
        //gen.writeStringField("secretInformation", animal.encryptSecretInformation());
        gen.writeEndObject();
    }
}
