package com.eviltester.scriptvalidator;

import com.eviltester.scriptformatter.files.ScriptFileReader;
import com.eviltester.scriptformatter.files.ScriptLinesProcessor;
import com.eviltester.scriptformatter.files.ScriptStringReader;
import com.eviltester.scriptformatter.script.ScriptValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CanValidateScripts {

    @Test
    public void firstDoMustHaveASay() throws URISyntaxException, IOException {

        String testDataFile = "first-do-missing-say.txt";
        final ScriptLinesProcessor parser = readAndParseFile(testDataFile);

        final ScriptValidator validator = new ScriptValidator();

        Assertions.assertFalse(validator.validate(testDataFile, parser.getScript()));

    }

    @Test
    public void secondDoMustHaveASay() throws URISyntaxException, IOException {

        String testDataFile = "second-do-missing-say.txt";
        final ScriptLinesProcessor parser = readAndParseFile(testDataFile);

        final ScriptValidator validator = new ScriptValidator();

        Assertions.assertFalse(validator.validate(testDataFile, parser.getScript()));

    }

    @Test
    public void lastDoMustHaveASay() throws URISyntaxException, IOException {

        String testDataFile = "last-do-missing-say.txt";
        final ScriptLinesProcessor parser = readAndParseFile(testDataFile);

        final ScriptValidator validator = new ScriptValidator();

        Assertions.assertFalse(validator.validate(testDataFile, parser.getScript()));

    }



    @Test
    public void firstSayMustHaveADo() throws URISyntaxException, IOException {

        String testDataFile = "first-say-missing-do.txt";
        final ScriptLinesProcessor parser = readAndParseFile(testDataFile);

        final ScriptValidator validator = new ScriptValidator();

        Assertions.assertFalse(validator.validate(testDataFile, parser.getScript()));

    }

    @Test
    public void secondSayMustHaveADo() throws URISyntaxException, IOException {

        String testDataFile = "second-say-missing-do.txt";
        final ScriptLinesProcessor parser = readAndParseFile(testDataFile);

        final ScriptValidator validator = new ScriptValidator();

        Assertions.assertFalse(validator.validate(testDataFile, parser.getScript()));

    }

    @Test
    public void lastSayMustHaveADo() throws URISyntaxException, IOException {

        String testDataFile = "last-say-missing-do.txt";
        final ScriptLinesProcessor parser = readAndParseFile(testDataFile);

        final ScriptValidator validator = new ScriptValidator();

        Assertions.assertFalse(validator.validate(testDataFile, parser.getScript()));

    }


    private ScriptLinesProcessor readAndParseFile(final String testDataFile) throws URISyntaxException, IOException {
        String script = new String(
                Files.readAllBytes(
                        Paths.get(getClass().getResource
                                (testDataFile).toURI())));
        System.out.println(script);

        ScriptStringReader reader = new ScriptStringReader(script);
        final ScriptLinesProcessor parser = new ScriptLinesProcessor();
        reader.registerLineByLineProcessor(parser);
        reader.read();
        return parser;
    }
}
