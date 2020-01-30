package com.eviltester.scriptformatter.formats;

import com.eviltester.scriptformatter.html.TableWriter;
import com.eviltester.scriptformatter.script.DoSayScript;
import com.eviltester.scriptformatter.script.ScriptSection;
import com.eviltester.scriptformatter.simplewriters.Htmlifier;
import com.eviltester.scriptformatter.simplewriters.SimpleWriter;

public class ScriptHtmlOutputter {
    private final SimpleWriter writer;

    public ScriptHtmlOutputter(final SimpleWriter writer) {
        this.writer = writer;
    }

    // TODO: allow multiple note sections and multiple tables
    // TODO: rethink this - bit clumsy
    public void output(final DoSayScript script) {
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
                    writer.write(Htmlifier.encode(line) + "<br>");
                    writer.newLine();
                }
            }

            if(section.isDo()){
                if(nextDo!=null && processingTable){
                    tableWriter.writeLine(brSeparatedSection(""),
                            brSeparatedSection(nextDo.getText()));
                }
                nextDo = section;
            }

            if(section.isSay()){


                if(!processingTable){
                    processingTable=true;
                    tableWriter = new TableWriter(writer);
                }

                if(nextDo==null){
                    tableWriter.writeLine(brSeparatedSection(section.getText()),
                            brSeparatedSection(""));
                }else {
                    tableWriter.writeLine(brSeparatedSection(section.getText()),
                            brSeparatedSection(nextDo.getText()));
                }

                nextDo=null; // clear the do so we know we have processed this
            }
        }

        if(processingTable){
            tableWriter.finish();
        }

    }

    private StringBuilder brSeparatedSection(final String text) {
        StringBuilder brSeparated = new StringBuilder();

        String[]sectionLines = text.split(String.format("%n"));
        for(String line : sectionLines) {
            brSeparated.append(Htmlifier.encode(line) + "<br>");
        }

        return brSeparated;
    }
}
