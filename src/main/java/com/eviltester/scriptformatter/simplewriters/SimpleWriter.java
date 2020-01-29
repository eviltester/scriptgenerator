package com.eviltester.scriptformatter.simplewriters;

public interface SimpleWriter {
    void newLine();

    void write(String someText);

    void close();
}
