package com.dragos.anki.commands;

import com.dragos.anki.config.AnkiConfiguration;
import com.dragos.anki.parser.KanjiKunOnVocabularyFileParser;
import com.dragos.anki.service.AnkiServiceImpl;
import com.dragos.anki.service.TemplateLoader;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command(
    name = "add-kanji",
    description = "Add kanji notes (漢字) to Anki deck"
)
public class AddKanjiCommand implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        TemplateLoader templateLoader = new TemplateLoader();
        String ankiCommandTemplate = templateLoader.loadTemplate(AnkiConfiguration.TEMPLATE_ADD_NOTE);
        
        AnkiServiceImpl ankiService = new AnkiServiceImpl();
        KanjiKunOnVocabularyFileParser parser = new KanjiKunOnVocabularyFileParser(
            ankiCommandTemplate, 
            AnkiConfiguration.DECK_KANJI, 
            ankiService
        );
        parser.parse();
        return 0;
    }
}
