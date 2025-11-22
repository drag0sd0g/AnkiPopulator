package com.dragos.anki.parser;

import com.dragos.anki.service.AnkiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class BasicVocabularyFileParserTest {

    @Mock
    private AnkiService mockAnkiService;

    private BasicVocabularyFileParser parser;
    private final String testTemplate = "{\"action\":\"addNote\",\"params\":{\"note\":{\"deckName\":\"%s\",\"fields\":{\"Expression\":\"%s\",\"Reading\":\"%s\",\"Meaning\":\"%s\"}}}}";
    private final String testDeck = "Test Deck";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        parser = new BasicVocabularyFileParser(testTemplate, testDeck, mockAnkiService);
    }

    @Test
    void testCreateAnkiJsonCommand_WithReading() {
        // Arrange
        String line = "単語, たんご - word";

        // Act
        String result = parser.createAnkiJsonCommand(line);

        // Assert
        assertTrue(result.contains("Test Deck"));
        assertTrue(result.contains("単語"));
        assertTrue(result.contains("word"));
    }

    @Test
    void testCreateAnkiJsonCommand_WithoutReading() {
        // Arrange
        String line = "言葉 - word";

        // Act
        String result = parser.createAnkiJsonCommand(line);

        // Assert
        assertTrue(result.contains("Test Deck"));
        assertTrue(result.contains("言葉"));
        assertTrue(result.contains("word"));
    }

    @Test
    void testCreateAnkiJsonCommand_ComplexExpression() {
        // Arrange
        String line = "例外, れいがい - exception";

        // Act
        String result = parser.createAnkiJsonCommand(line);

        // Assert
        assertTrue(result.contains("例外"));
        assertTrue(result.contains("exception"));
    }
}
