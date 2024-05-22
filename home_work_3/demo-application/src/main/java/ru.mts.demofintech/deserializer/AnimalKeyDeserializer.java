package ru.mts.demofintech.deserializer;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.mts.demofintech.entity.Animal;

import java.io.IOException;

public class AnimalKeyDeserializer extends KeyDeserializer {
    ObjectMapper objectMapper;

    public AnimalKeyDeserializer(ObjectMapper objectMapper) {
        this.objectMapper=objectMapper;
    }
    @Override
    public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException {
        if (key == null || key.isEmpty()) {
            return null;
        }
        String jsonKey = key.replace("{", "{\"").replace("}", "\"}")
                .replace(", ", "\", \"").replace(":", "\":\"");
        return objectMapper.readValue(jsonKey, Animal.class);
    }
}
