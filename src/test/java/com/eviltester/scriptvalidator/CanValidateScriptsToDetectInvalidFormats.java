package com.eviltester.scriptvalidator;

import com.eviltester.scriptformatter.files.ScriptLinesProcessor;
import com.eviltester.scriptformatter.files.ScriptStringReader;
import com.eviltester.scriptformatter.script.ScriptValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CanValidateScriptsToDetectInvalidFormats {

    // TODO: assert on error message returned by the validator
    @Test
    public void firstDoMustHaveASay() throws URISyntaxException, IOException {
        Assertions.assertFalse(
                thisFileIsValid("first-do-missing-say.txt"));
    }



    @Test
    public void secondDoMustHaveASay() throws URISyntaxException, IOException {
        Assertions.assertFalse(
                thisFileIsValid("second-do-missing-say.txt"));
    }

    @Test
    public void lastDoMustHaveASay() throws URISyntaxException, IOException {

        Assertions.assertFalse(
                thisFileIsValid("last-do-missing-say.txt"));
    }



    @Test
    public void firstSayMustHaveADo() throws URISyntaxException, IOException {
        Assertions.assertFalse(
                thisFileIsValid("first-say-missing-do.txt"));

    }

    @Test
    public void secondSayMustHaveADo() throws URISyntaxException, IOException {
        Assertions.assertFalse(
                thisFileIsValid("second-say-missing-do.txt"));
    }

    @Test
    public void lastSayMustHaveADo() throws URISyntaxException, IOException {
        Assertions.assertFalse(
                thisFileIsValid("last-say-missing-do.txt"));
    }

    private boolean thisFileIsValid(final String testDataFile) throws IOException, URISyntaxException {
        final ScriptLinesProcessor parser = readAndParseFile(testDataFile);

        final ScriptValidator validator = new ScriptValidator();

        return validator.validate(testDataFile, parser.getScript());
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
