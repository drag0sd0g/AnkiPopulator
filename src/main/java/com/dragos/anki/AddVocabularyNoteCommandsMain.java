package com.dragos.anki;

import com.dragos.anki.api.AnkiHttpClient;
import com.dragos.anki.parser.BasicVocabularyFileParser;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class AddVocabularyNoteCommandsMain {

    private static final String TARGET_DECK = "N1 - 昔の試験";

    public static void main(String[] args) throws IOException {
        File ankiCommandJSONFile = new File(ReadOnlyCommandsMain.class.getClassLoader().getResource("templates/addNote.json").getFile());
        String ankiCommandTemplate = FileUtils.readFileToString(ankiCommandJSONFile, "UTF-8");
        AnkiHttpClient ankiHttpClient = new AnkiHttpClient();
        BasicVocabularyFileParser parser = new BasicVocabularyFileParser(ankiCommandTemplate, TARGET_DECK, ankiHttpClient);
        parser.parse();
    }
}
