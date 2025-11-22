package com.dragos.anki;

import com.dragos.anki.api.AnkiHttpClient;
import com.dragos.anki.parser.ImiGaNiteiruKotobaParser;
import com.dragos.anki.service.AnkiServiceImpl;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class AddAllJPNotesMain {
    private static final String TARGET_DECK = "N1 - 語彙- 意味が似ている言葉";

    public static void main(String[] args) throws IOException {
        File ankiCommandJSONFile = new File(ReadOnlyCommandsMain.class.getClassLoader().getResource("templates/addNote.json").getFile());
        String ankiCommandTemplate = FileUtils.readFileToString(ankiCommandJSONFile, "UTF-8");
        AnkiServiceImpl ankiService = new AnkiServiceImpl(new AnkiHttpClient());
        ImiGaNiteiruKotobaParser parser = new ImiGaNiteiruKotobaParser(ankiCommandTemplate, TARGET_DECK, ankiService);
        parser.parse();
    }
}
