package com.dragos.anki.parser;

import com.dragos.anki.service.AnkiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class KanjiKunOnVocabularyFileParserTest {

    @Mock
    private AnkiService mockAnkiService;

    private KanjiKunOnVocabularyFileParser parser;
    private final String testTemplate = "{\"action\":\"addNote\",\"params\":{\"note\":{\"deckName\":\"%s\",\"fields\":{\"Expression\":\"%s\",\"Reading\":\"%s\",\"Meaning\":\"%s\"}}}}";
    private final String testDeck = "Test Deck";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        parser = new KanjiKunOnVocabularyFileParser(testTemplate, testDeck, mockAnkiService);
    }

    @Test
    void testCreateAnkiJsonCommand_WithKUN() {
        // Arrange
        String line = "漢KUN かん - kanji";

        // Act
        String result = parser.createAnkiJsonCommand(line);

        // Assert
        assertTrue(result.contains("Test Deck"));
        assertTrue(result.contains("漢"));
        assertTrue(result.contains("KUN かん"));
        assertTrue(result.contains("kanji"));
    }

    @Test
    void testCreateAnkiJsonCommand_WithON() {
        // Arrange
        String line = "字ON じ - character";

        // Act
        String result = parser.createAnkiJsonCommand(line);

        // Assert
        assertTrue(result.contains("Test Deck"));
        assertTrue(result.contains("字"));
        assertTrue(result.contains("ON じ"));
        assertTrue(result.contains("character"));
    }

    @Test
    void testCreateAnkiJsonCommand_RegularVocabulary() {
        // Arrange
        String line = "漢字, かんじ - kanji";

        // Act
        String result = parser.createAnkiJsonCommand(line);

        // Assert
        assertTrue(result.contains("Test Deck"));
        assertTrue(result.contains("漢字"));
        assertTrue(result.contains("kanji"));
    }
}
