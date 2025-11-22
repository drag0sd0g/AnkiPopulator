package com.dragos.anki.parser;

import com.dragos.anki.service.AnkiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class ImiGaNiteiruKotobaParserTest {

    @Mock
    private AnkiService mockAnkiService;

    private ImiGaNiteiruKotobaParser parser;
    private final String testTemplate = "{\"action\":\"addNote\",\"params\":{\"note\":{\"deckName\":\"%s\",\"fields\":{\"Expression\":\"%s\",\"Reading\":\"%s\",\"Meaning\":\"%s\"}}}}";
    private final String testDeck = "Test Deck";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        parser = new ImiGaNiteiruKotobaParser(testTemplate, testDeck, mockAnkiService);
    }

    @Test
    void testCreateAnkiJsonCommand_WithParentheses() {
        // Arrange
        String line = "これは（例文）です";

        // Act
        String result = parser.createAnkiJsonCommand(line);

        // Assert
        assertTrue(result.contains("Test Deck"));
        assertTrue(result.contains("（例文）"));
        assertTrue(result.contains("これは（例文）です"));
    }

    @Test
    void testCreateAnkiJsonCommand_MultipleWords() {
        // Arrange
        String line = "意味が（似ている）言葉";

        // Act
        String result = parser.createAnkiJsonCommand(line);

        // Assert
        assertTrue(result.contains("Test Deck"));
        assertTrue(result.contains("（似ている）"));
        assertTrue(result.contains("意味が（似ている）言葉"));
    }
}
