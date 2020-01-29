package com.eviltester.scriptformatter.formats;

import com.eviltester.scriptformatter.html.TableWriter;
import com.eviltester.scriptformatter.script.DoSayScript;
import com.eviltester.scriptformatter.script.ScriptSection;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ScriptHTMLOutputter {
    private final String outputPath;
    private final String outputFileName;
    private File outputFile;

    public ScriptHTMLOutputter(final String outputPath, final String outputFileName) {
        this.outputPath = outputPath;
        this.outputFileName = outputFileName;
        outputFile = new File(outputPath,outputFileName);
    }

    public String getPath(){
        return outputFile.getAbsolutePath();
    }

    //TODO add the script validation into the script object and have a `validate` method

    public void output(final DoSayScript script) throws IOException {

        if(outputFile.exists()){
            outputFile.delete();
        }

        new File(outputPath).mkdirs();

        final BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

        TableWriter tableWriter = new TableWriter(writer);
        boolean processingTable = false;

        ScriptSection nextDo=null;

        for(ScriptSection section : script.getSections()){

            if(section.isNote()){
                if(processingTable){
                    tableWriter.finish();
                    processingTable=false;
                }

                String[]sectionLines = section.getText().split(String.format("%n"));
                for(String line : sectionLines) {
                    writer.write(line + "<br>");
                    writer.newLine();
                }
            }

            if(section.isDo()){
                if(nextDo!=null && processingTable){
                    throw new RuntimeException("DO found without a SAY section " + nextDo.getText());
                }
                nextDo = section;
            }

            if(section.isSay()){
                if(nextDo==null){
                    throw new RuntimeException("Missing DO section for SAY Section " + section.getText());
                }

                if(!processingTable){
                    processingTable=true;
                    tableWriter = new TableWriter(writer);
                }

                tableWriter.writeLine(brSeparatedSection(section.getText()),
                        brSeparatedSection(nextDo.getText()));

                nextDo=null; // clear the do so we know we have processed this
            }
        }

        if(processingTable){
            tableWriter.finish();
        }

        writer.close();
    }

    private StringBuilder brSeparatedSection(final String text) {
        StringBuilder brSeparated = new StringBuilder();

        String[]sectionLines = text.split(String.format("%n"));
        for(String line : sectionLines) {
            brSeparated.append(line + "<br>");
        }

        return brSeparated;
    }
}
