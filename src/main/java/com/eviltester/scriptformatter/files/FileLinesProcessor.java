package com.eviltester.scriptformatter.files;

public interface FileLinesProcessor {
    void finishedEvent();

    void lineEvent(String line);

    void startingEvent(String name);
}
