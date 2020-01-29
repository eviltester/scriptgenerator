package com.eviltester.scriptformatter.script;

public class ScriptValidator {
    public void validate(final String name, final DoSayScript script) {

        ScriptSection nextDo=null;
        boolean processingDoSaySection = false;

        // TODO: this assumes that DO comes before SAY, we may want to configure this:
        // - to allow SAY to come before DO
        // - to allow multiple DO or SAY sections

        // TODO: create a list of violations and return true/false rather than runtime exception
        // TODO: create a line by line validator to allow reporting on line number in file

        for(ScriptSection section : script.getSections()) {

            if (section.isNote()) {

                if(processingDoSaySection){
                    processingDoSaySection=false;
                }
            }

            if (section.isDo()) {
                if (nextDo != null && processingDoSaySection) {
                    throw new RuntimeException(name + " DO found without a SAY section " + nextDo.getText());
                }
                nextDo = section;
            }

            if (section.isSay()) {
                if (nextDo == null) {
                    throw new RuntimeException(name + " Missing DO section for SAY Section " + section.getText());
                }

                processingDoSaySection=true;
                nextDo=null; // finished a row
            }
        }
    }
}
