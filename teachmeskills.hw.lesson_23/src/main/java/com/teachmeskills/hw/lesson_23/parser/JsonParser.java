package com.teachmeskills.hw.lesson_23.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teachmeskills.hw.lesson_23.exception.JsonFileParseException;
import com.teachmeskills.hw.lesson_23.model.Book;

import java.io.File;
import java.util.List;

public class JsonParser {

    public static List<Book> parseJsonFile(String fileName) throws JsonFileParseException {
        ObjectMapper objectMapper = new ObjectMapper();

        try {

            return objectMapper.readValue(new File(fileName),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Book.class));

        } catch (Exception e) {
            throw new JsonFileParseException("Error occurs during json file parsing: " + e.getMessage());
        }
    }
}

