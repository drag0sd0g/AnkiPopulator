package com.dragos.anki.commands;

import com.dragos.anki.config.AnkiConfiguration;
import com.dragos.anki.service.AnkiServiceImpl;
import com.dragos.anki.service.TemplateLoader;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command(
    name = "read-only",
    description = "Execute read-only commands (e.g., get deck names)"
)
public class ReadOnlyCommand implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        TemplateLoader templateLoader = new TemplateLoader();
        String ankiCommandAsJson = templateLoader.loadTemplate(AnkiConfiguration.TEMPLATE_READONLY_DECKNAMES);
        
        AnkiServiceImpl ankiService = new AnkiServiceImpl();
        String result = ankiService.postCommand(ankiCommandAsJson);
        System.out.println(result);
        return 0;
    }
}
