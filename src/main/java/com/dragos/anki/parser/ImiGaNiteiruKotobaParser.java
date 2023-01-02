package com.dragos.anki.parser;

import com.dragos.anki.api.AnkiHttpClient;

public class ImiGaNiteiruKotobaParser extends AbstractVocabularyFileParser {
    private final String ankiCommandTemplate;
    private final String deck;

    public ImiGaNiteiruKotobaParser(String ankiCommandTemplate, String deck, AnkiHttpClient ankiHttpClient) {
        super(ankiHttpClient);
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
