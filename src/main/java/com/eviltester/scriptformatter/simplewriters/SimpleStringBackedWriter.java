package com.eviltester.scriptformatter.simplewriters;

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

    public String getContents() {
        return backingString.toString();
    }
}
