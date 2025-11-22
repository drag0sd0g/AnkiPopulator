package com.dragos.anki.parser;

import com.dragos.anki.service.AnkiService;

public class AllJPParser extends AbstractVocabularyFileParser {
    private final String ankiCommandTemplate;
    private final String deck;

    public AllJPParser(String ankiCommandTemplate, String deck, AnkiService ankiService) {
        super(ankiService);
        this.ankiCommandTemplate = ankiCommandTemplate;
        this.deck = deck;
    }

    @Override
    public String createAnkiJsonCommand(String line) {
        String[] tokens = line.split("[-]");
        if (tokens.length < 3) {
            throw new IllegalArgumentException("Invalid line format. Expected format: 'expression-reading-meaning'. Got: " + line);
        }
        String expression = tokens[0].trim();
        String reading = tokens[1].trim();
        String meaning = tokens[2].trim();
        return String.format(ankiCommandTemplate, deck, expression, reading, meaning);
    }
}
