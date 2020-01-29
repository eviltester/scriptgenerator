package com.eviltester.scriptformatter.files;

import com.eviltester.scriptformatter.script.DoSayScript;

public class ScriptLinesProcessor implements FileLinesProcessor {

    boolean processingDo=false;
    boolean processingSay=false;
    boolean processingNote=false;

    StringBuilder currentSection;

    DoSayScript script;

    @Override
    public void finishedEvent() {
        endCurrentSection();
    }

    @Override
    public void lineEvent(final String line) {

        String trimmed = line.trim();
        if(trimmed.equalsIgnoreCase("do:")){
            endCurrentSection();
            processingDo = true;
        }

        if(trimmed.equalsIgnoreCase("say:")){
            endCurrentSection();
            processingSay=true;
        }

        currentSection.append(String.format("%s%n",trimmed));
    }

    private void endCurrentSection() {

        if(processingDo){
            script.addDo(currentSection);
        }
        if(processingSay){
            script.addSay(currentSection);
        }
        if(processingNote){
            script.addNote(currentSection);
        }
        processingDo=false;
        processingSay=false;
        processingNote = false;

        currentSection = new StringBuilder();
    }

    @Override
    public void startingEvent(String name) {
        processingDo=false;
        processingSay=false;
        processingNote = true;

        currentSection = new StringBuilder();

        script = new DoSayScript();
        script.named(name);
    }

    public DoSayScript getScript() {
        return script;
    }
}
