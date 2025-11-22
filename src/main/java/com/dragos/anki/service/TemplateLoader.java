package com.dragos.anki.service;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

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
     */
    public String loadTemplate(String resourcePath) throws IOException {
        File templateFile = new File(
            TemplateLoader.class.getClassLoader()
                .getResource(resourcePath)
                .getFile()
        );
        return FileUtils.readFileToString(templateFile, "UTF-8");
    }
}
