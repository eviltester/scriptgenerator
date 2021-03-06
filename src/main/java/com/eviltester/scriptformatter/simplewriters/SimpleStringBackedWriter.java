package com.eviltester.scriptformatter.simplewriters;

import java.util.List;

public class SimpleStringBackedWriter implements SimpleWriter {

    StringBuilder backingString;

    public SimpleStringBackedWriter(){
        backingString = new StringBuilder();
    }

    @Override
    public void newLine() {
        backingString.append(String.format("%n"));
    }

    @Override
    public void write(final String someText) {
        backingString.append(someText);
    }

    @Override
    public void close() {

    }

    @Override
    public SimpleWriter writeAll(final List<String> lines) {

        for(String line : lines){
            write(line);
            newLine();
        }

        return this;
    }

    public String getContents() {
        return backingString.toString();
    }
}
