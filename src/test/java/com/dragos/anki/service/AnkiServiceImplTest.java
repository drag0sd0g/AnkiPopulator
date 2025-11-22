package com.dragos.anki.service;

import com.dragos.anki.api.AnkiHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AnkiServiceImplTest {

    @Mock
    private AnkiHttpClient mockHttpClient;

    private AnkiServiceImpl ankiService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ankiService = new AnkiServiceImpl(mockHttpClient);
    }

    @Test
    void testPostCommand_Success() {
        // Arrange
        String command = "{\"action\":\"deckNames\",\"version\":6}";
        String expectedResponse = "{\"result\":[\"Default\"],\"error\":null}";
        when(mockHttpClient.postCommandToAnki(command)).thenReturn(expectedResponse);

        // Act
        String actualResponse = ankiService.postCommand(command);

        // Assert
        assertEquals(expectedResponse, actualResponse);
        verify(mockHttpClient, times(1)).postCommandToAnki(command);
    }

    @Test
    void testPostCommand_WithError() {
        // Arrange
        String command = "{\"action\":\"invalidAction\",\"version\":6}";
        String expectedResponse = "{\"result\":null,\"error\":\"invalid action\"}";
        when(mockHttpClient.postCommandToAnki(command)).thenReturn(expectedResponse);

        // Act
        String actualResponse = ankiService.postCommand(command);

        // Assert
        assertEquals(expectedResponse, actualResponse);
        verify(mockHttpClient, times(1)).postCommandToAnki(command);
    }

    @Test
    void testConstructor_WithoutParameter() {
        // Act
        AnkiServiceImpl service = new AnkiServiceImpl();

        // Assert - just verify it doesn't throw exception
        // The actual HTTP client will be created internally
        assertEquals(AnkiServiceImpl.class, service.getClass());
    }
}
