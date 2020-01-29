package com.eviltester.scriptformatter.html;

import com.eviltester.scriptformatter.files.ScriptLinesProcessor;
import com.eviltester.scriptformatter.files.ScriptStringReader;
import com.eviltester.scriptformatter.formats.ScriptHtmlOutputter;
import com.eviltester.scriptformatter.script.DoSayScript;
import com.eviltester.scriptformatter.simplewriters.SimpleStringBackedWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScriptHTMLOutputTest {

    @Test
    public void canCreateTestWithBlankDoAndSaySection() throws URISyntaxException, IOException {
        String script = new String(
                Files.readAllBytes(
                        Paths.get(getClass().getResource
                                ("script-blank-do.txt").toURI())));
        System.out.println(script);

        ScriptStringReader reader = new ScriptStringReader(script);

        final ScriptLinesProcessor parser = new ScriptLinesProcessor();
        reader.registerLineByLineProcessor(parser);
        reader.read();

        final DoSayScript parsedScript = parser.getScript();
        Assertions.assertEquals(7, parsedScript.getSections().size());

        SimpleStringBackedWriter writer = new SimpleStringBackedWriter();

        new ScriptHtmlOutputter(writer).output(parsedScript);

        System.out.println("***");
        System.out.println(writer.getContents());
        System.out.println("***");

        String output = new String(
                Files.readAllBytes(
                        Paths.get(getClass().getResource
                                ("script-blank-do-as-html.txt").toURI())));

        Assertions.assertEquals(output, writer.getContents());
    }
}
