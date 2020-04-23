package com.eviltester.scriptformatter.writers;

import com.eviltester.app.ScriptFormatterProcessor;
import com.eviltester.scriptformatter.formats.TimeEstimate;
import com.eviltester.scriptformatter.simplewriters.SimpleWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProcessorHtmlReport {
    private final SimpleWriter writer;

    public ProcessorHtmlReport(final SimpleWriter writer) {
        this.writer = writer;
    }

    public void output(final ScriptFormatterProcessor processor) {

        if(processor.getErrorReports().size()>0){
            writer.newLine();
            writer.write("<p><strong><a href='#errors'>WARNING - errors present see bottom of report</a></strong></p>");
            writer.newLine();
        }

        writer.write("<h2>TIME ESTIMATES</h2>");
        writer.newLine();

        for(TimeEstimate estimate : processor.getTimeEstimates()){
            writer.write("<p><a href='/openoutputfile?file=" + estimate.getText() + "' target='_blank'>" + estimate.getText() + "</a></p>");
            final String[] lines = estimate.report().split(String.format("%n"));
            writer.write(getUl(Arrays.asList(lines)));
            writer.newLine();
        }

        if(processor.getErrorReports().size()>0){

            writer.write("<h2 id='errors'>ERROR REPORTS</h2>");
            writer.newLine();
            writer.write(getUl(processor.getErrorReports()));
            writer.newLine();
        };

    }

    private String getUl(final List<String> lines) {
        StringBuilder ret = new StringBuilder();
        ret.append("<ul>");
        for(String line : lines){
            ret.append(String.format("<li>%s</li>%n", line));
        }
        ret.append("</ul>");
        return ret.toString();
    }
}
