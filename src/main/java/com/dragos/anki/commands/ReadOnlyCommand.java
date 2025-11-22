package com.dragos.anki.commands;

import com.dragos.anki.api.AnkiHttpClient;
import org.apache.commons.io.FileUtils;
import picocli.CommandLine.Command;

import java.io.File;
import java.util.concurrent.Callable;

@Command(
    name = "read-only",
    description = "Execute read-only commands (e.g., get deck names)"
)
public class ReadOnlyCommand implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        File ankiCommandJSONFile = new File(
            ReadOnlyCommand.class.getClassLoader()
                .getResource("readonly/deckNames.json")
                .getFile()
        );
        String ankiCommandAsJson = FileUtils.readFileToString(ankiCommandJSONFile, "UTF-8");
        AnkiHttpClient ankiHttpClient = new AnkiHttpClient();
        String result = ankiHttpClient.postCommandToAnki(ankiCommandAsJson);
        System.out.println(result);
        return 0;
    }
}
