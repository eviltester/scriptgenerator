package com.eviltester.scriptformatter.script;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ScriptValidator {

    List<String> errorReports = new ArrayList<>();

    public boolean validate(final String name, final DoSayScript script) {
        ScriptSection lastSection = null;
        boolean expectingDo=true;
        boolean processingDoSaySection = false;

        // TODO: this assumes that DO comes before SAY, we may want to configure this:
        // - to allow SAY to come before DO
        // - to allow multiple DO or SAY sections

        // TODO: create a list of violations and return true/false rather than runtime exception
        // TODO: create a line by line validator to allow reporting on line number in file

        for(ScriptSection section : script.getSections()) {

            if (section.isNote()) {
                // notes can be added anywhere, who cares about Notes
            }

            if (section.isDo()) {
                if (!expectingDo) {
                    addError(name + " DO found without a SAY section", lastSection);
                    return false;
                }
                expectingDo=false;
            }

            // check last section is a SAY
            if (section.isSay()) {
                if (expectingDo) {
                    addError(name + " SAY section found without a DO Section ", lastSection);
                    return false;
                }
                expectingDo=true;
            }

            lastSection = section;
        }

        // check last section is a SAY
        if (script.getSections().get(script.getSections().size()-1).isDo()) {
            errorReports.add("Ended on a DO section, missing a final SAY");
            return false;
        }
        return true;
    }

    private void addError(final String errorMessage, ScriptSection lastSection) {
        errorReports.add(errorMessage);
        if(lastSection==null){
            errorReports.add("No Previous Section");
        }else{
            errorReports.add(lastSection.getText());
        }
    }

    public List<String> getErrorReports() {
        return errorReports;
    }
}
