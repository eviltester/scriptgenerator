package com.eviltester.scriptformatter.html;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

public class TableWriter {
    private final BufferedWriter outputFileWriter;
    private boolean startedTable;

    public TableWriter(final BufferedWriter writer) {
        this.outputFileWriter = writer;
        startedTable=false;
    }

    public void writeLine(final StringBuilder sayThis, final StringBuilder doThis) {
        try {

            if(!startedTable){
                outputFileWriter.newLine();
                outputFileWriter.write("<style>table, th, td {border: 1px solid black;} td {width: 50%}</style>");
                outputFileWriter.write("<table>");
                outputFileWriter.newLine();
                startedTable=true;
            }

            outputFileWriter.newLine();
            outputFileWriter.write("<tr>");

                outputFileWriter.write("<td>");
                    outputFileWriter.write(sayThis.toString());
                outputFileWriter.write("</td>");

                outputFileWriter.write("<td>");
                    outputFileWriter.write(doThis.toString());
                outputFileWriter.write("</td>");
            outputFileWriter.write("</tr>");
            outputFileWriter.newLine();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void finish() {
        if(startedTable){
            try {
                outputFileWriter.newLine();
                outputFileWriter.write("</table>");
                outputFileWriter.newLine();

            } catch (IOException e) {
                e.printStackTrace();
            }
            startedTable=true;
        }
    }

}
