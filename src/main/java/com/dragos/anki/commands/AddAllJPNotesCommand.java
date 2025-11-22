package com.dragos.anki.commands;

import com.dragos.anki.api.AnkiHttpClient;
import com.dragos.anki.parser.ImiGaNiteiruKotobaParser;
import org.apache.commons.io.FileUtils;
import picocli.CommandLine.Command;

import java.io.File;
import java.util.concurrent.Callable;

@Command(
    name = "add-all-jp",
    description = "Add all Japanese notes (意味が似ている言葉) to Anki deck"
)
public class AddAllJPNotesCommand implements Callable<Integer> {
    private static final String TARGET_DECK = "N1 - 語彙- 意味が似ている言葉";

    @Override
    public Integer call() throws Exception {
        File ankiCommandJSONFile = new File(
            AddAllJPNotesCommand.class.getClassLoader()
                .getResource("templates/addNote.json")
                .getFile()
        );
        String ankiCommandTemplate = FileUtils.readFileToString(ankiCommandJSONFile, "UTF-8");
        AnkiHttpClient ankiHttpClient = new AnkiHttpClient();
        ImiGaNiteiruKotobaParser parser = new ImiGaNiteiruKotobaParser(
            ankiCommandTemplate, 
            TARGET_DECK, 
            ankiHttpClient
        );
        parser.parse();
        return 0;
    }
}
