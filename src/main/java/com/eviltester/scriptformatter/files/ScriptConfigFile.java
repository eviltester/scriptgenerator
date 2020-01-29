package com.eviltester.scriptformatter.files;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ScriptConfigFile {

    Properties properties = new Properties();
    final File propertiesFile;

    public ScriptConfigFile(){
        propertiesFile = new File(System.getProperty("user.dir"),
                                "config.properties");
    }

    public void read() {

        properties = new Properties();

        if(!propertiesFile.exists()){
            throw new RuntimeException("No Config File Found");
        }

        try {
            properties.load(new FileReader(propertiesFile));
        } catch (IOException e) {
            System.out.println("Error reading properties file: " + propertiesFile.getAbsolutePath());
            e.printStackTrace();
        }
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
