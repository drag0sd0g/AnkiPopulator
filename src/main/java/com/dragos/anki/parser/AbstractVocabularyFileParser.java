package com.dragos.anki.parser;

import com.dragos.anki.ReadOnlyCommandsMain;
import com.dragos.anki.service.AnkiService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public abstract class AbstractVocabularyFileParser implements VocabularyFileParser {

    private final AnkiService ankiService;

    public AbstractVocabularyFileParser(AnkiService ankiService) {
        this.ankiService = ankiService;
    }

    @Override
    public void parse() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Scanner scanner = new Scanner(new File(ReadOnlyCommandsMain.class.getClassLoader().getResource("vocabulary").getFile()));
        int successfullyAddedStats = 0;
        int errorsDuringAddingStats = 0;
        int emptyLinesStats = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (!line.isEmpty()) {
                String formattedJSONAnkiCommandRequest = createAnkiJsonCommand(line);
                String resultAsJsonString = ankiService.postCommand(formattedJSONAnkiCommandRequest);
                JsonNode resultAsJson = objectMapper.readTree(resultAsJsonString);
                String errorFieldInJsonResult = resultAsJson.get("error").asText();
                String resultFieldInJsonResult = resultAsJson.get("result").asText();
                if (!errorFieldInJsonResult.equals("null")) {
                    System.err.println(String.format("Error adding << %s >> Reason: %s", line, errorFieldInJsonResult));
                    errorsDuringAddingStats++;
                } else {
                    System.out.println(String.format("Successfully added << %s >> New ID is: %s", line, resultFieldInJsonResult));
                    successfullyAddedStats++;
                }

            } else {
                emptyLinesStats++;
            }
        }
        System.out.println("-------------<RESULTS>-------------------");
        System.out.println("Successfully added " + successfullyAddedStats);
        System.out.println("Errors during adding " + errorsDuringAddingStats);
        System.out.println("Empty lines in input file " + emptyLinesStats);
        System.out.println("-------------</RESULTS>------------------");
    }

    public abstract String createAnkiJsonCommand(String line);
}
