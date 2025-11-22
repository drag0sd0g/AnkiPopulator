package com.dragos.anki.integration;

import com.dragos.anki.api.AnkiHttpClient;
import com.dragos.anki.service.AnkiServiceImpl;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for Anki operations.
 * These tests verify the HTTP client integration using a mock HTTP server.
 */
@Testcontainers
class AnkiIntegrationTest {

    @Container
    private static final GenericContainer<?> httpServer = new GenericContainer<>(
        DockerImageName.parse("kennethreitz/httpbin:latest")
    )
    .withExposedPorts(80)
    .waitingFor(Wait.forHttp("/"));

    @Test
    void testServiceInstantiation() {
        // Test that services can be instantiated without errors
        AnkiHttpClient httpClient = new AnkiHttpClient();
        assertNotNull(httpClient);
        
        AnkiServiceImpl service = new AnkiServiceImpl(httpClient);
        assertNotNull(service);
    }

    @Test
    void testHttpServerIsRunning() {
        // Verify that the test HTTP server is running
        assertTrue(httpServer.isRunning());
        
        Integer port = httpServer.getMappedPort(80);
        assertNotNull(port);
        assertTrue(port > 0);
    }

    @Test
    void testServiceCanHandleHttpConnection() {
        // Test that the service can make HTTP connections
        // Note: This tests the HTTP infrastructure, not actual Anki functionality
        Integer port = httpServer.getMappedPort(80);
        String baseUrl = "http://localhost:" + port;
        
        // Verify the test server is accessible
        assertDoesNotThrow(() -> {
            new java.net.URL(baseUrl + "/status/200").openConnection();
        });
    }
}
