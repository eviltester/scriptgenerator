package com.eviltester.app;

import com.eviltester.scriptformatter.files.ScriptConfigFile;
import com.eviltester.scriptformatter.files.ScriptPaths;
import com.eviltester.scriptformatter.simplewriters.SimpleStringBackedWriter;
import com.eviltester.scriptformatter.simplewriters.SimpleSystemOutBackedWriter;
import com.eviltester.scriptformatter.simplewriters.SimpleWriter;
import com.eviltester.scriptformatter.writers.ProcessorHtmlReport;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

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
            processor.validateAllScripts();

            res.status(200);
            res.body(getReportFrom(processor));

            return res.body();

        });

        get("/validate", (req, res) -> {
            ScriptFormatterProcessor processor = new ScriptFormatterProcessor(paths);
            processor.validateAllScripts();

            res.status(200);
            res.body(getReportFrom(processor));

            return res.body();

        });

        get("/generate", (req, res) -> {
            ScriptFormatterProcessor processor = new ScriptFormatterProcessor(paths);
            processor.outputAllScripts();

            res.status(200);
            res.body(getReportFrom(processor));

            return res.body();

        });

        get("/openoutputfile", (req, res) -> {
            String filename = req.queryParams("file");
            if(filename==null){
                filename="";
            }
            if(new File(paths.getInputPath(), filename).exists()){
                File outputFile = new File(paths.getOutputPath(), filename + ".html");
                if(outputFile.exists()){
                    // read the file
                    try
                    {
                        res.body(new String ( Files.readAllBytes( outputFile.toPath() ) ));
                    }
                    catch (IOException e)
                    {
                        res.body(e.getMessage());
                        e.printStackTrace();
                    }
                }
            }

            res.status(200);
            return res.body();

        });
    }

    private static String getReportFrom(final ScriptFormatterProcessor processor) {

        SimpleWriter writer = new SimpleStringBackedWriter();

        new ProcessorHtmlReport(writer).output(processor);


        return "<html><head><title>Script Generator</title></head><body>" +
                "<div class='actionbuttons'>"+
                "<form><button formaction='/validate'>Validate Only</button></form>"+
                "<form><button formaction='/generate'>Validate And Generate</button></form>"+
                ((SimpleStringBackedWriter)writer).getContents() +
                "</body></html>";

    }


}
