package com.eviltester.scriptformatter.simplewriters;

import java.util.List;

public interface SimpleWriter {
    void newLine();

    void write(String someText);

    void close();

    SimpleWriter writeAll(List<String> lines);
}
