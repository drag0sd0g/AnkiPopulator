package com.dragos.anki.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TemplateLoaderTest {

    private TemplateLoader templateLoader;

    @BeforeEach
    void setUp() {
        templateLoader = new TemplateLoader();
    }

    @Test
    void testLoadTemplate_ValidPath() throws IOException {
        // Act
        String template = templateLoader.loadTemplate("templates/addNote.json");

        // Assert
        assertNotNull(template);
        assertFalse(template.isEmpty());
        assertTrue(template.contains("addNote") || template.contains("action"));
    }

    @Test
    void testLoadTemplate_InvalidPath() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> {
            templateLoader.loadTemplate("nonexistent/template.json");
        });
    }
}
