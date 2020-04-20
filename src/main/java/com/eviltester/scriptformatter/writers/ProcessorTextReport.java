package com.eviltester.scriptformatter.writers;

import com.eviltester.app.ScriptFormatterProcessor;
import com.eviltester.scriptformatter.formats.TimeEstimate;
import com.eviltester.scriptformatter.simplewriters.SimpleWriter;

public class ProcessorTextReport {
    private final SimpleWriter writer;

    public ProcessorTextReport(final SimpleWriter writer) {
        this.writer = writer;
    }

    public void output(final ScriptFormatterProcessor processor) {
        writer.write("TIME ESTIMATES");
        writer.newLine();
        writer.write("=============");
        writer.newLine();
        for(TimeEstimate estimate : processor.getTimeEstimates()){
            writer.write(estimate.report());
            writer.newLine();
        }

        if(processor.getErrorReports().size()>0){

            writer.write("ERROR REPORTS");
            writer.newLine();
            writer.write("=============");
            writer.newLine();
            writer.writeAll(processor.getErrorReports());
            throw new RuntimeException("Error processing Reports");
        };

    }
}
