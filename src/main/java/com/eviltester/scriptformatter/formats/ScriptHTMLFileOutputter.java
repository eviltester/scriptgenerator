package com.eviltester.scriptformatter.formats;

import com.eviltester.scriptformatter.script.DoSayScript;
import com.eviltester.scriptformatter.simplewriters.SimpleFileBackedWriter;
import com.eviltester.scriptformatter.simplewriters.SimpleWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ScriptHTMLFileOutputter {
    private final String outputPath;
    private final String outputFileName;
    private File outputFile;

    public ScriptHTMLFileOutputter(final String outputPath, final String outputFileName) {
        this.outputPath = outputPath;
        this.outputFileName = outputFileName;
        outputFile = new File(outputPath,outputFileName);
    }

    public String getPath(){
        return outputFile.getAbsolutePath();
    }

    public void output(final DoSayScript script) throws IOException {

        if(outputFile.exists()){
            outputFile.delete();
        }

        new File(outputPath).mkdirs();

        final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile));

        SimpleWriter writer = new SimpleFileBackedWriter(bufferedWriter);

        new ScriptHtmlOutputter(writer).output(script);

        writer.close();
    }


}
