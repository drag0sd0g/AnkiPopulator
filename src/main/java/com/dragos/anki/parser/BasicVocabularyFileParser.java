package com.dragos.anki.parser;

import com.dragos.anki.service.AnkiService;

public class BasicVocabularyFileParser extends AbstractVocabularyFileParser {

    private final String ankiCommandTemplate;
    private final String deck;

    public BasicVocabularyFileParser(String ankiCommandTemplate, String deck, AnkiService ankiService) {
        super(ankiService);
        this.ankiCommandTemplate = ankiCommandTemplate;
        this.deck = deck;
    }

    @Override
    public String createAnkiJsonCommand(String line) {
        int indexOfFirstDash = line.indexOf("-");
        String japanese = line.substring(0, indexOfFirstDash).trim();
        String meaning = line.substring(indexOfFirstDash + 1).trim();
        int indexOfLastComma = japanese.lastIndexOf(",");
        String expression = indexOfLastComma == -1 ? japanese : japanese.substring(0, indexOfLastComma);
        String reading = indexOfLastComma == -1 ? "" : japanese;
        return String.format(ankiCommandTemplate, deck, expression, reading, meaning);
    }
}
