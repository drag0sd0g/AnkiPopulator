package com.dragos.anki;

import com.dragos.anki.api.AnkiHttpClient;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class ReadOnlyCommandsMain {

    public static void main(String[] args) throws IOException {
        File ankiCommandJSONFile = new File(ReadOnlyCommandsMain.class.getClassLoader().getResource("readonly/deckNames.json").getFile());
        String ankiCommandAsJson = FileUtils.readFileToString(ankiCommandJSONFile, "UTF-8");
        AnkiHttpClient ankiHttpClient = new AnkiHttpClient();
        ankiHttpClient.postCommandToAnki(ankiCommandAsJson);
    }
}
