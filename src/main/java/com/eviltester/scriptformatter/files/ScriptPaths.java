package com.eviltester.scriptformatter.files;

public class ScriptPaths {

    private String inputPath;
    private String outputPath;

    public ScriptPaths(){
        this.inputPath = "";
        this.outputPath = "";
    }

    public ScriptPaths readFrom(final String inputPath) {
        if(inputPath!=null){
            this.inputPath = inputPath;
        }
        return this;
    }

    public ScriptPaths outputTo(final String outputPath) {
        if(outputPath!=null) {
            this.outputPath = outputPath;
        }
        return this;
    }

    public boolean hasInputPath() {
        return inputPath.length()>0;
    }

    public String getInputPath() {
        return this.inputPath;
    }

    public String getOutputPath() {
        return this.outputPath;
    }
}
