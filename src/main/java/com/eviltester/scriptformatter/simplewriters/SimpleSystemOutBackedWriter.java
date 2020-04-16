package com.eviltester.scriptformatter.simplewriters;

import java.util.List;

public class SimpleSystemOutBackedWriter implements SimpleWriter {

    public SimpleSystemOutBackedWriter(){

    }

    @Override
    public void newLine() {
        System.out.print(String.format("%n"));
    }

    @Override
    public void write(final String someText) {
        System.out.print(someText);
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

}
