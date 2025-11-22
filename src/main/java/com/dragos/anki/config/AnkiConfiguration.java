package com.dragos.anki.config;

/**
 * Configuration constants for Anki decks and templates.
 * Follows Single Responsibility Principle (SRP) by managing only configuration.
 */
public class AnkiConfiguration {
    
    // Deck names
    public static final String DECK_ALL_JP_NOTES = "N1 - 語彙- 意味が似ている言葉";
    public static final String DECK_VOCABULARY = "N1 - 昔の試験";
    public static final String DECK_KANJI = "N1 - 漢字";
    
    // Template paths
    public static final String TEMPLATE_ADD_NOTE = "templates/addNote.json";
    public static final String TEMPLATE_READONLY_DECKNAMES = "readonly/deckNames.json";
    
    // Resource paths
    public static final String RESOURCE_VOCABULARY = "vocabulary";
    
    private AnkiConfiguration() {
        // Prevent instantiation
    }
}
