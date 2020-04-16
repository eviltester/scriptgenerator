package com.eviltester.scriptformatter.simplewriters;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class SimpleFileBackedWriter implements SimpleWriter{
    private final BufferedWriter output;

    public SimpleFileBackedWriter(final BufferedWriter bufferedWriter) {
        this.output = bufferedWriter;
    }

    @Override
    public void newLine() {
        try {
            output.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(final String someText) {
        try {
            output.write(someText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
