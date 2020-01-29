package com.eviltester.scriptformatter.simplewriters;

import java.io.BufferedWriter;
import java.io.IOException;

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
}
