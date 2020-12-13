package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertNotNull;


public class TransferObjectTestUnits {
    private static final String BASE_JSON_PATH = "src/test/resources/dto/";

    public static void assertSerialization(String file, Class serialisationClass) throws IOException {
        String eventJson = new String(Files.readAllBytes(Paths.get(BASE_JSON_PATH + file)));
        ObjectMapper objectMapper = new ObjectMapper();
        Object object = objectMapper.readValue(eventJson, serialisationClass);
        String jsonStr = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        assertNotNull(jsonStr);
    }
}
