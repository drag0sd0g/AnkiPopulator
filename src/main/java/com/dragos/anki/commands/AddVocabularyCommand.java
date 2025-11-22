package com.dragos.anki.commands;

import com.dragos.anki.api.AnkiHttpClient;
import com.dragos.anki.parser.BasicVocabularyFileParser;
import org.apache.commons.io.FileUtils;
import picocli.CommandLine.Command;

import java.io.File;
import java.util.concurrent.Callable;

@Command(
    name = "add-vocabulary",
    description = "Add vocabulary notes (昔の試験) to Anki deck"
)
public class AddVocabularyCommand implements Callable<Integer> {
    private static final String TARGET_DECK = "N1 - 昔の試験";

    @Override
    public Integer call() throws Exception {
        File ankiCommandJSONFile = new File(
            AddVocabularyCommand.class.getClassLoader()
                .getResource("templates/addNote.json")
                .getFile()
        );
        String ankiCommandTemplate = FileUtils.readFileToString(ankiCommandJSONFile, "UTF-8");
        AnkiHttpClient ankiHttpClient = new AnkiHttpClient();
        BasicVocabularyFileParser parser = new BasicVocabularyFileParser(
            ankiCommandTemplate, 
            TARGET_DECK, 
            ankiHttpClient
        );
        parser.parse();
        return 0;
    }
}
