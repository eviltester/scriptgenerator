package com.eviltester.scriptformatter.files;

import java.util.ArrayList;
import java.util.List;

public class ScriptStringReader {
    private final String scriptContents;
    private final List<ScriptLinesProcessor> lineProcessors;

    public ScriptStringReader(final String script) {
        this.scriptContents = script;
        lineProcessors = new ArrayList<>();
    }

    public ScriptStringReader registerLineByLineProcessor(final ScriptLinesProcessor parser) {
        lineProcessors.add(parser);
        return this;
    }

    public void read() {

        letProcessorsKnowWeAreStarting("string script");

        String[] lines = scriptContents.split(String.format("%n"));
        for(String line : lines){
            letProcessorsKnowAboutThisLine(line);
        }

        letProcessorsKnowWeHaveFinished();
    }

    private void letProcessorsKnowWeHaveFinished() {
        for(ScriptLinesProcessor processor : lineProcessors){
            processor.finishedEvent();
        }
    }

    private void letProcessorsKnowAboutThisLine(final String line) {
        for(ScriptLinesProcessor processor : lineProcessors){
            processor.lineEvent(line);
        }
    }

    private void letProcessorsKnowWeAreStarting(final String inputFileName) {
        for(ScriptLinesProcessor processor : lineProcessors){
            processor.startingEvent(inputFileName);
        }
    }
}
