package com.eviltester.app;

import com.eviltester.scriptformatter.files.ScriptFileReader;
import com.eviltester.scriptformatter.files.ScriptLinesProcessor;
import com.eviltester.scriptformatter.files.ScriptPaths;
import com.eviltester.scriptformatter.formats.ScriptHTMLFileOutputter;
import com.eviltester.scriptformatter.formats.ScriptTimeEstimator;
import com.eviltester.scriptformatter.formats.TimeEstimate;
import com.eviltester.scriptformatter.script.ScriptValidator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScriptFormatterProcessor {
    private final ScriptPaths paths;
    private final ArrayList<String> errorReports;
    private final ArrayList<TimeEstimate> timeEstimates;

    public ScriptFormatterProcessor(final ScriptPaths paths) {
        this.paths = paths;
        this.errorReports = new ArrayList<>();
        this.timeEstimates = new ArrayList<>();

    }

    public boolean outputAllScripts(){
        return process(true);
    }

    public boolean validateAllScripts(){
        return process(false);
    }


    public boolean process(boolean output){
        File inputFolder = new File(paths.getInputPath());
        File[] files = inputFolder.listFiles();
        Arrays.sort(files);

        this.errorReports.clear();
        this.timeEstimates.clear();

        for (final File fileEntry : files) {

            if(fileEntry.getName().endsWith(".md")){

                final ScriptFileReader scriptFileReader = new ScriptFileReader(paths.getInputPath(), fileEntry.getName());
                final ScriptLinesProcessor parser = new ScriptLinesProcessor();
                scriptFileReader.registerLineByLineProcessor(parser);

                try {
                    scriptFileReader.read();
                } catch (IOException e) {
                    e.printStackTrace();
                    errorReports.add("Error Reading " + fileEntry.getName() + " - " + e.getMessage());
                    continue;
                }

                ScriptValidator validator = new ScriptValidator();

                if(!validator.validate(fileEntry.getName(), parser.getScript())) {
                    errorReports.add("FAILED VALIDATION: " + fileEntry.getName());
                    errorReports.addAll(validator.getErrorReports());
                    continue;
                }

                final TimeEstimate estimates = new ScriptTimeEstimator().calculateEstimates(parser.getScript());
                timeEstimates.add(estimates);

                    if(output) {
                        final ScriptHTMLFileOutputter outputter = new ScriptHTMLFileOutputter(paths.getOutputPath(), fileEntry.getName() + ".html");

                        if (scriptFileReader.getPath().equalsIgnoreCase(outputter.getPath())) {

                            errorReports.add("Input file same as output file: " + scriptFileReader.getPath());
                            continue;
                        }

                        try {
                            outputter.output(parser.getScript());
                        } catch (IOException e) {
                            e.printStackTrace();
                            errorReports.add("Error Writing " + outputter.getPath() + " - " + e.getMessage());
                            continue;
                        }
                    }
                }
        }

        if(errorReports.size()>0){
            return false;
        }else{
            return true;
        }
    }

    public List<String> getErrorReports() {
        return errorReports;
    }

    public ArrayList<TimeEstimate> getTimeEstimates(){
        return this.timeEstimates;
    }
}
