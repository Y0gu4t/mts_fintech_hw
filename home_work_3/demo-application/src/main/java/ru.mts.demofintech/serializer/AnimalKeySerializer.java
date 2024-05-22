package ru.mts.demofintech.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import ru.mts.demofintech.entity.Animal;

import java.io.IOException;

public class AnimalKeySerializer extends JsonSerializer<Animal> {
    @Override
    public void serialize(Animal animal, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeFieldName(animal.toJsonString());
    }
}
