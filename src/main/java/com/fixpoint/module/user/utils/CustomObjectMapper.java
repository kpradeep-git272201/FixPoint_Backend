package com.fixpoint.module.user.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Component;

@Component
public class CustomObjectMapper {
    private final ObjectMapper objectMapper;

    public CustomObjectMapper() {
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules(); // for JavaTimeModule, etc.
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false); // Important
    }

    public <T> T convert(Object source, Class<T> targetClass) {
        return objectMapper.convertValue(source, targetClass);
    }

    public String toJson(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    public <T> T fromJson(String json, Class<T> targetClass) throws JsonProcessingException {
        return objectMapper.readValue(json, targetClass);
    }

    public ObjectMapper getMapper() {
        return objectMapper;
    }
}
