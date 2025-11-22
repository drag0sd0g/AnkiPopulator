package com.dragos.anki.parser;

import com.dragos.anki.service.AnkiService;

public class ImiGaNiteiruKotobaParser extends AbstractVocabularyFileParser {
    private final String ankiCommandTemplate;
    private final String deck;

    public ImiGaNiteiruKotobaParser(String ankiCommandTemplate, String deck, AnkiService ankiService) {
        super(ankiService);
        this.ankiCommandTemplate = ankiCommandTemplate;
        this.deck = deck;
    }

    @Override
    public String createAnkiJsonCommand(String line) {
        int indexOfFirstOpenParamthesis = line.indexOf("（");
        int indexOfFirstClosedParamthesis = line.indexOf("）");
        String expression = line.substring(indexOfFirstOpenParamthesis, indexOfFirstClosedParamthesis + 1);
        return String.format(ankiCommandTemplate, deck, expression, line, "");
    }
}
