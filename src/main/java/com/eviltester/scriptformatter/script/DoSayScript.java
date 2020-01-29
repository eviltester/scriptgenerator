package com.eviltester.scriptformatter.script;

import java.util.ArrayList;
import java.util.List;

public class DoSayScript {

    private List<ScriptSection> scriptSections;
    private String name;

    public DoSayScript(){
        this.scriptSections = new ArrayList();
    }

    public void addDo(final StringBuilder currentSection) {
        scriptSections.add(ScriptSection.getDo(currentSection.toString()));
    }

    public void addSay(final StringBuilder currentSection) {
        scriptSections.add(ScriptSection.getSay(currentSection.toString()));
    }

    public void addNote(final StringBuilder currentSection) {
        scriptSections.add(ScriptSection.getNote(currentSection.toString()));
    }

    public List<ScriptSection> getSections() {
        return scriptSections;
    }

    public void named(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
