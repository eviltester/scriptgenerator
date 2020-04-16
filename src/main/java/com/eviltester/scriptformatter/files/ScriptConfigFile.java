package com.eviltester.scriptformatter.files;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ScriptConfigFile {

    Properties properties = new Properties();
    final File propertiesFile;
    List<String> errorReports;

    public ScriptConfigFile(){
        propertiesFile = new File(System.getProperty("user.dir"),
                                "config.properties");
        errorReports = new ArrayList<>();
    }

    public boolean read() {

        properties = new Properties();

        errorReports.clear();

        if(!propertiesFile.exists()){
            errorReports.add("No Config File Found");
            return false;
        }

        try {
            properties.load(new FileReader(propertiesFile));
        } catch (IOException e) {
            errorReports.add("Error reading properties file: " + propertiesFile.getAbsolutePath());
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public List<String>getErrorReports(){
        return errorReports;
    }

    public void configure(final ScriptPaths paths) {
        if(properties.containsKey("inputPath")){
            paths.readFrom((String)properties.get("inputPath"));
        }
        if(properties.containsKey("outputPath")){
            paths.outputTo((String)properties.get("outputPath"));
        }
    }
}
