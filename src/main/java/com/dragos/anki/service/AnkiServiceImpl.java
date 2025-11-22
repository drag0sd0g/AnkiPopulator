package com.dragos.anki.service;

import com.dragos.anki.api.AnkiHttpClient;

/**
 * Implementation of AnkiService using HTTP client.
 * Follows Dependency Inversion Principle (DIP) by depending on abstractions.
 */
public class AnkiServiceImpl implements AnkiService {
    private final AnkiHttpClient httpClient;

    public AnkiServiceImpl(AnkiHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public AnkiServiceImpl() {
        this(new AnkiHttpClient());
    }

    @Override
    public String postCommand(String ankiCommandAsJson) {
        return httpClient.postCommandToAnki(ankiCommandAsJson);
    }
}
