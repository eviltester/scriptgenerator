package com.eviltester.scriptformatter.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScriptFileReader {

    private final String inputPath;
    private final String inputFileName;
    private final List<ScriptLinesProcessor> lineProcessors;
    private File inputFile;

    public ScriptFileReader(final String inputPath, final String inputFileName) {
        this.inputPath = inputPath;
        this.inputFileName = inputFileName;
        this.lineProcessors = new ArrayList();
        inputFile = new File(inputPath, inputFileName);
    }

    public ScriptFileReader registerLineByLineProcessor(ScriptLinesProcessor processor){
        this.lineProcessors.add(processor);
        return this;
    }

    public void read() throws IOException {

        // read the file
        if (!inputFile.exists()) {
            throw new RuntimeException("Input file not exist: " + inputFile.getAbsolutePath());
        }

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

        letProcessorsKnowWeAreStarting(inputFileName);

        String nextLine = reader.readLine();

        while(nextLine!=null){
            letProcessorsKnowAboutThisLine(nextLine);
            nextLine = reader.readLine();
        }

        reader.close();

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

    public String getPath() {
        return inputFile.getAbsolutePath();
    }
}
