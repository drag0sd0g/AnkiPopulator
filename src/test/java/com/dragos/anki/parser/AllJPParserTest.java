package com.dragos.anki.parser;

import com.dragos.anki.service.AnkiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class AllJPParserTest {

    @Mock
    private AnkiService mockAnkiService;

    private AllJPParser parser;
    private final String testTemplate = "{\"action\":\"addNote\",\"params\":{\"note\":{\"deckName\":\"%s\",\"fields\":{\"Expression\":\"%s\",\"Reading\":\"%s\",\"Meaning\":\"%s\"}}}}";
    private final String testDeck = "Test Deck";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        parser = new AllJPParser(testTemplate, testDeck, mockAnkiService);
    }

    @Test
    void testCreateAnkiJsonCommand_ValidFormat() {
        // Arrange
        String line = "言葉-ことば-word";

        // Act
        String result = parser.createAnkiJsonCommand(line);

        // Assert
        assertTrue(result.contains("Test Deck"));
        assertTrue(result.contains("言葉"));
        assertTrue(result.contains("ことば"));
        assertTrue(result.contains("word"));
    }

    @Test
    void testCreateAnkiJsonCommand_InvalidFormat() {
        // Arrange
        String line = "言葉-word"; // Missing one field

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            parser.createAnkiJsonCommand(line);
        });
        assertTrue(exception.getMessage().contains("Invalid line format"));
    }

    @Test
    void testCreateAnkiJsonCommand_WithSpaces() {
        // Arrange
        String line = "単語 - たんご - word";

        // Act
        String result = parser.createAnkiJsonCommand(line);

        // Assert
        assertTrue(result.contains("単語"));
        assertTrue(result.contains("たんご"));
        assertTrue(result.contains("word"));
    }
}
