import com.eviltester.scriptformatter.html.ScriptFormatter;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.Arrays;
import java.util.Properties;

public class ScriptFormatterTest {

    @Test
    public void givenAScriptFolderCreateAHTMLFiles() throws IOException {

        // hard code your paths here, or use a config.properties file in user.dir
        String inputPath=""; // = "/my/input/path";
        String outputPath=""; // = "/my/output/path";

        if(inputPath.length()==0){
            // if you have config.properties in the root of
            // the project then read the
            // properties from there, leaving the Strings above blank
            // inputPath = /my/input/path
            // outputPath = /my/output/path
            Properties properties = new Properties();

            final File propertiesFile = new File(System.getProperty("user.dir"),
                    "config.properties");
            if(!propertiesFile.exists()){
                throw new RuntimeException("No Paths specified");
            }
            properties.load(new FileReader(propertiesFile));
            inputPath = (String)properties.get("inputPath");
            outputPath = (String)properties.get("outputPath");

        }

        File inputFolder = new File(inputPath);
        File[] files = inputFolder.listFiles();
        Arrays.sort(files);

        for (final File fileEntry : files) {
            if(fileEntry.getName().endsWith(".md")){
                new ScriptFormatter(inputPath, fileEntry.getName(), outputPath, fileEntry.getName()+".html").output();
            }
        }





    }
}
