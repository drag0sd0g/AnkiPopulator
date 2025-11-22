package com.dragos.anki.commands;

import com.dragos.anki.api.AnkiHttpClient;
import com.dragos.anki.parser.KanjiKunOnVocabularyFileParser;
import org.apache.commons.io.FileUtils;
import picocli.CommandLine.Command;

import java.io.File;
import java.util.concurrent.Callable;

@Command(
    name = "add-kanji",
    description = "Add kanji notes (漢字) to Anki deck"
)
public class AddKanjiCommand implements Callable<Integer> {
    private static final String TARGET_DECK = "N1 - 漢字";

    @Override
    public Integer call() throws Exception {
        File ankiCommandJSONFile = new File(
            AddKanjiCommand.class.getClassLoader()
                .getResource("templates/addNote.json")
                .getFile()
        );
        String ankiCommandTemplate = FileUtils.readFileToString(ankiCommandJSONFile, "UTF-8");
        AnkiHttpClient ankiHttpClient = new AnkiHttpClient();
        KanjiKunOnVocabularyFileParser parser = new KanjiKunOnVocabularyFileParser(
            ankiCommandTemplate, 
            TARGET_DECK, 
            ankiHttpClient
        );
        parser.parse();
        return 0;
    }
}
