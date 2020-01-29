package com.eviltester.scriptformatter.html;

import com.eviltester.scriptformatter.simplewriters.SimpleWriter;

public class TableWriter {
    private final SimpleWriter output;
    private boolean startedTable;

    public TableWriter(final SimpleWriter writer) {
        this.output = writer;
        startedTable=false;
    }

    public void writeLine(final StringBuilder sayThis, final StringBuilder doThis) {

        if(!startedTable){
            output.newLine();
            output.write("<style>table, th, td {border: 1px solid black;} td {width: 50%}</style>");
            output.write("<table>");
            output.newLine();
            startedTable=true;
        }

        output.newLine();
        output.write("<tr>");

            output.write("<td>");
                output.write(sayThis.toString());
            output.write("</td>");

            output.write("<td>");
                output.write(doThis.toString());
            output.write("</td>");
        output.write("</tr>");
        output.newLine();

    }

    public void finish() {
        if(startedTable){

            output.newLine();
            output.write("</table>");
            output.newLine();

            startedTable=true;
        }
    }

}
