package com.eviltester.app;

import com.eviltester.scriptformatter.files.ScriptConfigFile;
import com.eviltester.scriptformatter.files.ScriptPaths;
import com.eviltester.scriptformatter.simplewriters.SimpleStringBackedWriter;
import com.eviltester.scriptformatter.simplewriters.SimpleSystemOutBackedWriter;
import com.eviltester.scriptformatter.simplewriters.SimpleWriter;
import com.eviltester.scriptformatter.writers.ProcessorTextReport;

import static spark.Spark.get;

public class ScriptFormatterWebApp {

    public static void main(String[] args) {

        ScriptPaths paths = new ScriptPaths();

        // hard code your paths here, or use a config.properties file in user.dir
        paths.readFrom(""); // set your input path here if necessary e.g. "/my/input/path"
        paths.outputTo(""); // set your output path here if necessary e.g. "/my/output/path"

        // if you have config.properties in the root of
        // the project then read the
        // properties from there, leaving the Strings above blank
        // inputPath = /my/input/path
        // outputPath = /my/output/path
        if(!paths.hasInputPath()){
            ScriptConfigFile configFile = new ScriptConfigFile();
            if(!configFile.read()){
                new SimpleSystemOutBackedWriter().writeAll(configFile.getErrorReports());
                throw new RuntimeException("Error processing Config File");
            }
            configFile.configure(paths);
        }

        System.out.println("http://localhost:4567");
        System.out.println("Input: " + paths.getInputPath());
        System.out.println("Output: " + paths.getOutputPath());

        get("/", (req, res) -> {
            ScriptFormatterProcessor processor = new ScriptFormatterProcessor(paths);
            processor.outputAllScripts();

            SimpleWriter writer = new SimpleStringBackedWriter();

            new ProcessorTextReport(writer).output(processor);

            res.status(200);
            res.body("<html><head><title>Script Generator</title></head><body><pre>" +
                    ((SimpleStringBackedWriter)writer).getContents()
                    +"</pre></body></html>");
            return res.body();
        });
    }


}
