package com.dragos.anki.service;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Service for loading templates from resources.
 * Follows Single Responsibility Principle (SRP).
 */
public class TemplateLoader {
    
    /**
     * Loads a template file from the classpath resources.
     * 
     * @param resourcePath The path to the resource file
     * @return The content of the template file
     * @throws IOException If the file cannot be read
     * @throws IllegalArgumentException If the resource is not found
     */
    public String loadTemplate(String resourcePath) throws IOException {
        URL resourceUrl = TemplateLoader.class.getClassLoader().getResource(resourcePath);
        if (resourceUrl == null) {
            throw new IllegalArgumentException("Resource not found: " + resourcePath);
        }
        File templateFile = new File(resourceUrl.getFile());
        return FileUtils.readFileToString(templateFile, StandardCharsets.UTF_8);
    }
}
