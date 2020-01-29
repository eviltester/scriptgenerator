package com.eviltester.scriptformatter.script;

public class ScriptSection {


    private final String type;
    private final String text;

    public ScriptSection(final String type, final String sectionText) {
        this.type = type;
        this.text = sectionText;
    }

    public static ScriptSection getDo(final String sectionText) {
        return new ScriptSection("DO", sectionText);
    }

    public static ScriptSection getSay(final String sectionText) {
        return new ScriptSection("SAY", sectionText);
    }

    public static ScriptSection getNote(final String sectionText) {
        return new ScriptSection("NOTE", sectionText);
    }

    public boolean isSay() {
        return "SAY".equals(this.type);
    }

    public String getText() {
        return this.text;
    }

    public boolean isNote() {
        return "NOTE".equals(this.type);
    }

    public boolean isDo() {
        return "DO".equals(this.type);
    }
}
