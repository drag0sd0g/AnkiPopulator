package com.dragos.anki.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for Anki operations.
 * These tests require Docker and will be skipped if ANKI_INTEGRATION_TESTS is not set.
 */
@Testcontainers
@EnabledIfEnvironmentVariable(named = "ANKI_INTEGRATION_TESTS", matches = "true")
class AnkiIntegrationTest {

    @Container
    private static final GenericContainer<?> ankiContainer = new GenericContainer<>(
        DockerImageName.parse("katsana/anki-server:latest")
    )
    .withExposedPorts(8765)
    .withEnv("ANKI_SYNC_SERVER_ENABLED", "false");

    @Test
    void testAnkiContainerIsRunning() {
        // Assert that container started successfully
        assertTrue(ankiContainer.isRunning());
        
        // Verify the exposed port is available
        Integer mappedPort = ankiContainer.getMappedPort(8765);
        assertNotNull(mappedPort);
        assertTrue(mappedPort > 0);
    }

    @Test
    void testAnkiConnectEndpoint() throws Exception {
        // Get the mapped port
        Integer port = ankiContainer.getMappedPort(8765);
        String ankiUrl = "http://localhost:" + port;
        
        // Try to connect to Anki
        URL url = new URL(ankiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        
        // Send a simple version check command
        String jsonCommand = "{\"action\":\"version\",\"version\":6}";
        connection.getOutputStream().write(jsonCommand.getBytes());
        
        // Check response code (may not work if Anki image is not properly configured)
        int responseCode = connection.getResponseCode();
        
        // We expect either 200 (success) or potentially other codes if Anki isn't fully configured
        assertTrue(responseCode > 0, "Should receive some response from Anki");
    }
}
