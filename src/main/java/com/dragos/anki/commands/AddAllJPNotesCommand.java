package com.dragos.anki.commands;

import com.dragos.anki.config.AnkiConfiguration;
import com.dragos.anki.parser.ImiGaNiteiruKotobaParser;
import com.dragos.anki.service.AnkiServiceImpl;
import com.dragos.anki.service.TemplateLoader;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command(
    name = "add-all-jp",
    description = "Add all Japanese notes (意味が似ている言葉) to Anki deck"
)
public class AddAllJPNotesCommand implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        TemplateLoader templateLoader = new TemplateLoader();
        String ankiCommandTemplate = templateLoader.loadTemplate(AnkiConfiguration.TEMPLATE_ADD_NOTE);
        
        AnkiServiceImpl ankiService = new AnkiServiceImpl();
        ImiGaNiteiruKotobaParser parser = new ImiGaNiteiruKotobaParser(
            ankiCommandTemplate, 
            AnkiConfiguration.DECK_ALL_JP_NOTES, 
            ankiService
        );
        parser.parse();
        return 0;
    }
}
