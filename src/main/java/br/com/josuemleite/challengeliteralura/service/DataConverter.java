package br.com.josuemleite.challengeliteralura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataConverter {
    private ObjectMapper mapper = new ObjectMapper();

    public <T> T obtainData(String json, Class<T> targetClass) {
        try {
            return mapper.readValue(json, targetClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
