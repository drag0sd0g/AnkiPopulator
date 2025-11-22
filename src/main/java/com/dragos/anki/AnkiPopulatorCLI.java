package com.dragos.anki;

import com.dragos.anki.commands.*;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
    name = "anki-populator",
    description = "A tool for automating the population of Anki decks with Japanese vocabulary, kanji, and expressions.",
    version = "1.0.0",
    mixinStandardHelpOptions = true,
    subcommands = {
        AddAllJPNotesCommand.class,
        AddVocabularyCommand.class,
        AddKanjiCommand.class,
        ReadOnlyCommand.class
    }
)
public class AnkiPopulatorCLI implements Runnable {

    public static void main(String[] args) {
        int exitCode = new CommandLine(new AnkiPopulatorCLI()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        // Show help when no command is specified
        CommandLine.usage(this, System.out);
    }
}
