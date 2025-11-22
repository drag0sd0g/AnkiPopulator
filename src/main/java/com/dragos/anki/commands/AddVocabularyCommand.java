package com.dragos.anki.commands;

import com.dragos.anki.config.AnkiConfiguration;
import com.dragos.anki.parser.BasicVocabularyFileParser;
import com.dragos.anki.service.AnkiServiceImpl;
import com.dragos.anki.service.TemplateLoader;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command(
    name = "add-vocabulary",
    description = "Add vocabulary notes (昔の試験) to Anki deck"
)
public class AddVocabularyCommand implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        TemplateLoader templateLoader = new TemplateLoader();
        String ankiCommandTemplate = templateLoader.loadTemplate(AnkiConfiguration.TEMPLATE_ADD_NOTE);
        
        AnkiServiceImpl ankiService = new AnkiServiceImpl();
        BasicVocabularyFileParser parser = new BasicVocabularyFileParser(
            ankiCommandTemplate, 
            AnkiConfiguration.DECK_VOCABULARY, 
            ankiService
        );
        parser.parse();
        return 0;
    }
}
