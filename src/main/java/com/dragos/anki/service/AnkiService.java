package com.dragos.anki.service;

/**
 * Interface for interacting with Anki via HTTP API.
 * Follows Interface Segregation Principle (ISP) by providing focused API operations.
 */
public interface AnkiService {
    /**
     * Posts a command to Anki and returns the response.
     * 
     * @param ankiCommandAsJson The command in JSON format
     * @return The response from Anki as a JSON string
     */
    String postCommand(String ankiCommandAsJson);
}
